package com.epam.valkaryne.spectrumevo.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.epam.valkaryne.spectrumevo.repository.datamodel.Game

@Database(entities = [Game::class], version = 1)
abstract class SpectrumDatabase : RoomDatabase() {

    abstract fun spectrumDao(): SpectrumDao

    companion object {
        private var instance: SpectrumDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): SpectrumDatabase? {
            synchronized(lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SpectrumDatabase::class.java,
                        "Spectrum"
                    ).build()
                }
                return instance
            }
        }
    }
}