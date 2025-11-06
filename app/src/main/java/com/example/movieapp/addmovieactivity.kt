package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class addmovieactivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_addmovieactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find Views
        val etMovieTitle = findViewById<EditText>(R.id.etMovieTitle)
        val etDirector = findViewById<EditText>(R.id.etDirector)
        val etRatings = findViewById<EditText>(R.id.etRatings)
        val etComments = findViewById<EditText>(R.id.etComments)
        val tvError = findViewById<TextView>(R.id.tvError)
        val btnSaveMovie = findViewById<Button>(R.id.btnSaveMovie)
        val btnBacktoMain = findViewById<Button>(R.id.btnBacktoMain)

        btnSaveMovie.setOnClickListener {
            // Clear previous errors
            tvError.text = ""

            // Get all text from EditText fields
            val movieTitle = etMovieTitle.text.toString()
            val director = etDirector.text.toString()
            val ratingString = etRatings.text.toString()
            val comments = etComments.text.toString()

            // Check for empty fields
            if (movieTitle.isEmpty() || director.isEmpty() || ratingString.isEmpty()) {
                tvError.text = "Error: Title, Directors, and Rating are required."
                return@setOnClickListener // Exit the click listener
            }

            try {
                val ratingInt = ratingString.toInt()
                // Check if the rating is within the valid range
                if (ratingInt < 1 || ratingInt > 5) {
                    tvError.text = "Error: Rating must be between 1 and 5."
                    Log.e("AddMovie Error", "Invalid rating: $ratingInt")
                    return@setOnClickListener // Exit the click listener
                }

                // Add data to your central data store
                MovieData.addmovie(movieTitle, director, ratingInt, comments)

                // Give user feedback and close this screen
                Toast.makeText(this, "$movieTitle Added", Toast.LENGTH_SHORT).show()
                finish() // Closes the activity and returns to the previous one

            } catch (e: NumberFormatException) {
                tvError.text = "Error: Rating must be a valid number."
                Log.e("AddMovie Error", "NumberFormatException: $ratingString", e)
            }
        }

        btnBacktoMain.setOnClickListener {
            finish() // Closes the current activity
        }
    }
}
