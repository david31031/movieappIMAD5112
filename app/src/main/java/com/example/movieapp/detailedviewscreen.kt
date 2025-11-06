package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class detailedviewscreen : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detailedviewscreen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        }
        val lvMovies = findViewById<ListView>(R.id.lvMovies)
        val btnBack = findViewById<Button>(R.id.btnBack)
        // format list to show all details
        val displayList = ArrayList<String>()
        // loop in data and build display list
        for (i in 0 until MovieData.movieTitles.size) { val movieTitle = MovieData.movieTitles[i]
        val director = MovieData.director[i]
        val rating = MovieData.ratings[i]
        val comment = MovieData.comments[i]
        val displayString = "$movieTitle ($rating/5) \nDirected by : $director \nComments: $comment"
        displayList.add(displayString)
        }
        // create adapter
        val adapter = ArrayAdapter(this , android.R.layout.simple_list_item_1 , displayList)
        lvMovies.adapter = adapter
        // navigation back to main
        btnBack.setOnClickListener { finish() }




    }
}