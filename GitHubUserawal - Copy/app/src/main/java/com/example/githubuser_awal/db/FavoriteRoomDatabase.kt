package com.example.githubuser_awal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubuser_awal.data.model.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = true)
abstract class FavoriteRoomDatabase : RoomDatabase() {
    abstract fun favDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteRoomDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): FavoriteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteRoomDatabase::class.java, "GithubUserDB"
                    )
                        .build()
                }
            }
            return INSTANCE as FavoriteRoomDatabase
        }
    }
}