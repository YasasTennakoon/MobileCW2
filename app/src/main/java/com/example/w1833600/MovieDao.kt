package com.example.w1833600

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao{
    @Query("Select * from movie")
    suspend fun getAll(): List<Movie>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(vararg user: Movie)
    @Insert
    suspend fun insertAll(vararg users: Movie)
    @Query("delete from movie ")
    suspend fun deleteAll()
}