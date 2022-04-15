package com.example.w1833600

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchMovie : AppCompatActivity() {
    lateinit var movieName:EditText
    lateinit var retrieveMovie:Button
    lateinit var movie:TextView
    lateinit var addMovie:Button
    //The data which are retrieved form the web Api
    private var title=""
    private var year=""
    private var rate=""
    private var released=""
    private var runtime=""
    private var genre=""
    private var director=""
    private var writer=""
    private var actor=""
    private var plot=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)
        movieName=findViewById(R.id.movieName)
        retrieveMovie=findViewById(R.id.retrieveMovie)
        movie=findViewById(R.id.movie)
        movie.movementMethod= ScrollingMovementMethod()
        addMovie=findViewById(R.id.addMovie)


        retrieveMovie.setOnClickListener {
            configWebApi()
        }

        addMovie.setOnClickListener {
            addDataToDatabase()
        }
    }

    fun addDataToDatabase()
    {
        val movieDatabase=Room.databaseBuilder(this, MovieDatabase::class.java,"Movie").build()
        val movieDao=movieDatabase.movieDao()
        runBlocking {
            launch {
                var movie=Movie(title,year,rate,released,runtime,genre,director,writer,actor,plot)
                movieDao.insertMovie(movie)
              }
        }
    }

    fun configWebApi()
    {
        var ApiKey = resources.getString(R.string.MY_API_KEY)
        var name=movieName.text
        var readMovie=StringBuffer()
        val webUrl="http://www.omdbapi.com/?t=$name&apikey=$ApiKey"
        val url = URL(webUrl)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        var retrievingData:String=""
        runBlocking {
            launch {

                withContext(Dispatchers.IO) {
                    var bf = BufferedReader(InputStreamReader(connection.inputStream))
                    var line: String? = bf.readLine()
                    while (line != null) {
                        readMovie.append(line + "\n")
                        line = bf.readLine()
                    }
                    //retrievingData=getData(readMovie)

                }
            }
        }
        getData(readMovie)
        //movie.text=retrievingData

    }

    fun getData(retrieveMovie:StringBuffer) {

        val json = JSONObject(retrieveMovie.toString())
         title=json["Title"] as String
         year=json["Year"] as String
         rate=json["Rated"] as String
         released=json["Released"] as String
         runtime=json["Runtime"] as String
         genre=json["Genre"] as String
         director=json["Director"] as String
         writer=json["Writer"] as String
         actor=json["Actors"] as String
         plot=json["Plot"] as String
         movie.text="Title :$title \n\n Year :$year \n\n Rating :$rate \n\n Released :$released \n\n Runtime :$runtime\n\n Genre :$genre \n\n " +
                "Direcors :$director \n\n Writer :$writer \n\n Actors :$actor \n\n Plot :$plot"
        //movieData+="$year "

        //return movieData

    }
}