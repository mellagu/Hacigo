package com.mellagusty.hacigo_mobileapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KiddoJournalEntity::class], version = 1, exportSchema = false)
abstract class KiddoJournalDatabase : RoomDatabase() {

    abstract fun kiddoJournalDao(): KiddoJournalDao

    companion object {
        @Volatile
        private var INSTANCE: KiddoJournalDatabase? = null

        //We're going use the same instance for our database
        fun getDatabase(context: Context): KiddoJournalDatabase {
            //if instance is not null then returning the instance
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KiddoJournalDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}