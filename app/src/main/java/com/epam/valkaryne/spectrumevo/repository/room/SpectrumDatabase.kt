package com.epam.valkaryne.spectrumevo.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

@Database(entities = [Game::class], version = 1)
abstract class SpectrumDatabase : RoomDatabase() {
    abstract fun spectrumDao(): SpectrumDao
}