package com.example.w1833600

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    lateinit var searchMovies:Button;
    lateinit var result:TextView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchMovies=findViewById(R.id.SearchMovie)
        result=findViewById(R.id.result)
        val movieDatabase=Room.databaseBuilder(this, MovieDatabase::class.java,"Movie").build()
        val movieDao=movieDatabase.movieDao()
        runBlocking {
            launch {
                val movie1=Movie(1,"The Shawshank Redemption",1994,"R","14 Oct 1994",
                "142 min","Drama","Frank Darabont","Stephen King, Frank Darabont","Tim Robbins, Morgan Freeman, Bob Gunton","Two imprisoned men bond over a number of years, finding solace\n" +
                            "and eventual redemption through acts of common decency.")
                movieDao.insertMovie(movie1)

            }
        }
        searchMovies.setOnClickListener {
            var searchPage=Intent(this,SearchMovie::class.java)
            startActivity(searchPage)
        }


    }


}