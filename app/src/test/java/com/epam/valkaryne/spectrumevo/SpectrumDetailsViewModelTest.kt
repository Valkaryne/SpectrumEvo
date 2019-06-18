package com.epam.valkaryne.spectrumevo

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.epam.valkaryne.spectrumevo.repository.SpectrumRepository
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game
import com.epam.valkaryne.spectrumevo.viewmodel.SpectrumDetailsViewModel
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
class SpectrumDetailsViewModelTest : Spek({

    emulateInstantTaskExecutorRule()

    val repositoryMock: SpectrumRepository = mockk()
    val testViewModel = SpectrumDetailsViewModel(repositoryMock)

    val gameDataObserver: Observer<Game> = mockk(relaxed = true)

    val networkData: PagedList<Game> = mockk(relaxed = true)
    val networkErrorData: PagedList<Game> = mockPagedList(emptyList())

    val localData: List<Game> = mockk(relaxed = true)
    val localErrorData: List<Game> = emptyList()

    val game: Game = mockk(relaxed = true)
    val errorGameData = Game()

    given("Data Loading: Success Scenario") {
        beforeEachTest {
            clearMocks(repositoryMock)
            testViewModel.game.observeForever(gameDataObserver)
        }

        afterEachTest {
            testViewModel.game.removeObserver(gameDataObserver)
        }

        describe("Data Loading: Success Scenario") {
            beforeEachTest {
                coEvery { repositoryMock.getGames().value } returns networkData
                coEvery { repositoryMock.getGamesFromLocal().value } returns localData
                coEvery { repositoryMock.deleteGameFromRoom(game) } returns Unit
                coEvery { repositoryMock.insertGameIntoRoom(game) } returns Unit
                testViewModel.delete(game)
                testViewModel.insert(game)
            }

            it("Game Data should be changed in database") {
                verify { gameDataObserver.onChanged(game) }
            }
        }

        describe("Data Loading: Error Scenario") {
            beforeEachTest {
                coEvery { repositoryMock.getGames().value } returns networkErrorData
                coEvery { repositoryMock.getGamesFromLocal().value } returns localErrorData
                coEvery { repositoryMock.deleteGameFromRoom(errorGameData) } returns Unit
                coEvery { repositoryMock.insertGameIntoRoom(errorGameData) } returns Unit
                testViewModel.delete(errorGameData)
                testViewModel.insert(errorGameData)
            }

            it("Game Data wasn't changed in database") {
                verify { gameDataObserver.onChanged(errorGameData) }
            }
        }
    }
})