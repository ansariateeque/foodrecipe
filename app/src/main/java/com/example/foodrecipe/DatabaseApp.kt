package com.example.foodrecipe

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], exportSchema = false, version = 1)
 abstract class DatabaseApp:RoomDatabase() {
    abstract fun getDao():Dao
}


