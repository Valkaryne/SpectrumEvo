package com.epam.valkaryne.spectrumevo

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.paging.PagedList
import org.jetbrains.spek.api.dsl.Spec
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

fun Spec.emulateInstantTaskExecutorRule() {

    beforeEachTest {
        ArchTaskExecutor.getInstance().setDelegate(SingleThreadTaskExecutor())
    }

    afterEachTest {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}

fun <T> mockPagedList(list: List<T>): PagedList<T> {
    val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
    Mockito.`when`(pagedList[ArgumentMatchers.anyInt()]).then { invocation ->
        val index = invocation.arguments.first() as Int
        list[index]
    }
    Mockito.`when`(pagedList.size).thenReturn(list.size)
    return pagedList
}

class SingleThreadTaskExecutor : TaskExecutor() {
    override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
    }

    override fun isMainThread(): Boolean {
        return true
    }

    override fun postToMainThread(runnable: Runnable) {
        runnable.run()
    }
}