package com.epam.valkaryne.spectrumevo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumListViewModel
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class SpectrumListViewModelTest : Spek({

    emulateInstantTaskExecutorRule()

    val repositoryMock: SpectrumRepository = mockk()
    val testViewModel = SpectrumListViewModel(repositoryMock)

    val networkListObserver: Observer<PagedList<Game>> = mockk(relaxed = true)
    val localListObserver: Observer<List<Game>> = mockk(relaxed = true)

    val networkData: PagedList<Game> = mockk(relaxed = true)
    val networkErrorData: PagedList<Game> = mockPagedList(emptyList())

    val localData: List<Game> = mockk(relaxed = true)
    val localErrorData: List<Game> = emptyList()

    given("Data Loading: Success Scenario") {
        beforeEachTest {
            clearMocks(repositoryMock)
            testViewModel.networkGamesList.observeForever(networkListObserver)
            testViewModel.localGamesList.observeForever(localListObserver)
        }

        afterEachTest {
            testViewModel.networkGamesList.removeObserver(networkListObserver)
            testViewModel.localGamesList.removeObserver(localListObserver)
        }

        describe("Data Loading: Success Scenario") {
            beforeEachTest {
                coEvery { repositoryMock.getGames().value } returns networkData
                coEvery { repositoryMock.getGamesFromLocal().value } returns localData
                testViewModel.refresh()
            }

            it("loaded default game data should be posted on list") {
                verify { networkListObserver.onChanged(networkData) }
            }

            it("loaded spectrum game data should be posted on pages") {
                verify { localListObserver.onChanged(localData) }
            }
        }

        describe("Data Loading: Error Scenario") {
            beforeEachTest {
                coEvery { repositoryMock.getGames().value } returns networkErrorData
                coEvery { repositoryMock.getGamesFromLocal().value } returns localErrorData
                testViewModel.refresh()
            }

            it("loaded default game data is empty") {
                verify { networkListObserver.onChanged(networkErrorData) }
            }

            it("loaded spectrum game data is empty") {
                verify { localListObserver.onChanged(localErrorData) }
            }
        }
    }
})