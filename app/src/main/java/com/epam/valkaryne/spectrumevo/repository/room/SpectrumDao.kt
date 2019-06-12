package com.epam.valkaryne.spectrumevo.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

@Dao
interface SpectrumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    @Query("SELECT * FROM games")
    fun getGames(): List<Game>

    @Delete
    fun deleteGame(game: Game)
}