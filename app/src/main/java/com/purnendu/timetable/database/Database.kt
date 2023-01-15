package com.purnendu.timetable.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TaskModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {

    abstract fun TaskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: com.purnendu.timetable.database.Database? = null

        fun getDataBase(context: Context): com.purnendu.timetable.database.Database {
            if (INSTANCE == null) {
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                       com.purnendu.timetable.database.Database::class.java,
                        "TaskDb"
                    ).build()
                }
            }
            return INSTANCE!!

        }
    }

}