package com.example.foodrecipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {


    @Query("select * from recipe")
    fun getall(): List<Recipe>??
}