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
        var title=json["Title"] as String
        var year=json["Year"] as String
        var rate=json["Rated"] as String
        var released=json["Released"] as String
        var runtime=json["Runtime"] as String
        var genre=json["Genre"] as String
        var director=json["Director"] as String
        var writer=json["Writer"] as String
        var actor=json["Actors"] as String
        var plot=json["Plot"] as String
        movie.text="Title :$title \n\n Year :$year \n\n Rating :$rate \n\n Released :$released \n\n Runtime :$runtime\n\n Genre :$genre \n\n " +
                "Direcors :$director \n\n Writer :$writer \n\n Actors :$actor \n\n Plot :$plot"
        //movieData+="$year "

        //return movieData

    }
}