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

                val movie1=Movie("The Shawshank Redemption",1994,"R","14 Oct 1994",
                "142 min","Drama","Frank Darabont","Stephen King, Frank Darabont","Tim Robbins, Morgan Freeman, Bob Gunton","Two imprisoned men bond over a number of years, finding solace\n" +
                            "and eventual redemption through acts of common decency.")


                val movie2=Movie("Batman: The Dark Knight Returns, Part 1",2012,"PG-13","25 Sep 2012",
                    "76 min","Animation, Action, Crime, Drama, Thriller","Jay Oliva","Bob Kane (character created by: Batman), Frank Miller (comic book), Klaus Janson (comic book), Bob\n" +
                            "Goodman","Peter Weller, Ariel Winter, David Selby, Wade Williams","Batman has not been seen for ten years. A new breed\n" +
                            "of criminal ravages Gotham City, forcing 55-year-old Bruce Wayne back\n" +
                            "into the cape and cowl. But, does he still have what it takes to fight\n" +
                            "crime in a new era?")


                val movie3=Movie("The Lord of the Rings: The Return of the King",2003,"PG-13","17 Dec 2003",
                "201 min","Action, Adventure, Drama","Peter Jackson","J.R.R. Tolkien, Fran Walsh, Philippa Boyens","Elijah Wood, Viggo Mortensen, Ian McKellen","Gandalf and Aragorn lead the World of Men against Sauron's\n" +
                            "army to draw his gaze from Frodo and Sam as they approach Mount Doom\n" +
                            "with the One Ring.")
                movieDao.insertMovie(movie1)
                movieDao.insertMovie(movie2)
                movieDao.insertMovie(movie3)
                //movieDao.deleteAll()
                val users: List<Movie> = movieDao.getAll()
                for (u in users) {
                    result.append("\n ${u.title} ${u.year}")
                }

            }
        }

        searchMovies.setOnClickListener {
            var searchPage=Intent(this,SearchMovie::class.java)
            startActivity(searchPage)
        }


    }


}