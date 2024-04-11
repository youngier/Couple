package com.cloudon.couple.ui.anniversay.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cloudon.couple.application.MainApplication
import com.cloudon.couple.ui.anniversay.AnniversaryBean
import com.cloudon.couple.ui.anniversay.AnniversaryDao
import com.cloudon.couple.ui.anniversay.type.TypeBean
import com.cloudon.couple.ui.anniversay.type.TypeDao

@Database(version = 1, exportSchema = false, entities = [AnniversaryBean::class, TypeBean::class])
abstract class AnniversaryDatabase : RoomDatabase() {

    abstract fun getDao(): AnniversaryDao
    abstract fun getTypeDao(): TypeDao

    companion object {

        var db: AnniversaryDatabase? = null

        fun getDatabase(): AnniversaryDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    MainApplication.context,
                    AnniversaryDatabase::class.java,
                    "anniversary.db"
                ).setJournalMode(JournalMode.TRUNCATE)
                    .build()
            }
            return db!!
        }
    }

}