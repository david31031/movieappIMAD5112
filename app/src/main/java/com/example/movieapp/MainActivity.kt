package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() { lateinit var tvAverageRating: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v , insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left , systemBars.top , systemBars.right , systemBars.bottom)
            insets
        } // find views
        tvAverageRating = findViewById(R.id.tvAverageRating)
        val btnAddMovie = findViewById<Button>(R.id.btnAddMovie)
        val btnViewReviews = findViewById<Button>(R.id.btnViewReviews)
        // Navigation Buttons
        btnAddMovie.setOnClickListener {
            val intent = Intent(this , Addmovieactivity::class.java)
            startActivity(intent)
        }
        btnViewReviews.setOnClickListener {
            val intent = Intent(this , detailedviewscreen::class.java)
            startActivity(intent)
        }
    }
    // Accuracy in calculating and displaying average
    override fun onResume() {
        super.onResume()
        updateAverageRating()
    }
    // Update Average Rating
    @SuppressLint("DefaultLocale")
    private fun updateAverageRating() {
        val averageRating = MovieData.calculateAverageRating()
        tvAverageRating.text = String.format("AverageRating: %.1f/5" , averageRating)
    }
}
