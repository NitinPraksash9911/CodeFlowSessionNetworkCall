package com.example.codeflowsession.localdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalUser::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {

        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context):UserDatabase {
            if (instance == null) {

                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_db")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance!!

        }

    }



}