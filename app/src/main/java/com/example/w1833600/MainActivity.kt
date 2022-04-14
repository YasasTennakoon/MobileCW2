package com.example.w1833600

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
               // val movie1=Movie("The Shawshank Redemption",1994)
            }
        }

        //       searchMovies.setOnClickListener withContext@{

//            var MY_API_KEY = resources.getString(R.string.MY_API_KEY)
//            var json:String="";
//            val stb = StringBuilder("")
//            var movie_name:String="Matrix";
//            var url_string = "http://www.omdbapi.com/?t=$movie_name&apikey=$MY_API_KEY";
//            val url = URL(url_string)
//            val con = url.openConnection() as HttpURLConnection
//            con.toString()
//            print(con.toString());
//
//            print(stb);
//            result.text=con.toString();

//            val url = URL(url_string)
//            val con = url.openConnection() as HttpURLConnection
//            con.setRequestMethod("GET")
//            print(con.toString())

            //print(con.toString());


     //   }

    }

//    fun getWeather() {
//
//        var MY_API_KEY = resources.getString(R.string.MY_API_KEY)
//        var movie_name:String="Matrix";
//        var url_string = "http://www.omdbapi.com/?t=$movie_name&apikey=$MY_API_KEY";
//
//        var data: String = ""
//
//        // start the fetching of data in the background
//        runBlocking {
//            withContext(Dispatchers.IO) {
//                // this will contain the whole of JSON
//                val stb = StringBuilder("")
//
//                val url = URL(url_string)
//                val con = url.openConnection() as HttpURLConnection
//                val bf: BufferedReader
//                try {
//                    bf = BufferedReader(InputStreamReader(con.inputStream))
//                }
//                catch (e: IOException) {
//                    e.printStackTrace()
//                    return@withContext
//                }
//
//                var line = bf.readLine()
//                while (line != null) {
//                    stb.append(line)
//                    line = bf.readLine()
//                }
//
//                print(stb.toString())
//
//
//            }
//
//        }
//    }
}