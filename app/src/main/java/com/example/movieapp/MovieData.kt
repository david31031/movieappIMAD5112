package com.example.movieapp
import android.util.Log
object MovieData {// Parallel Arrays
    val movieTitles = ArrayList<String>()
    val director= ArrayList<String>()
    val ratings = ArrayList<Int>()
    val comments = ArrayList<String>()
    // sample data
    init {
        Log.i("MovieData", "Initializing Sample Data...")
        addmovie("The GodFather", "Francis Ford Coppola", 5, "Great movie, A Masteroiece in Cinema.")
        addmovie("The Dark Knight", "Christopher Nolan", 5, "Iconic Performance")
        addmovie("Pulp Fiction", "Quentin Tarantino", 4, "Quirky and Captivating")
    }
    // A Helper Function to add a movie , used by "init" and add screen
    fun addmovie (movieTitle: String, directors: String, rating: Int, comment: String) {
        movieTitles.add(movieTitle)
        director.add(directors)
        ratings.add(rating)
        comments.add(comment)
        Log.i("MovieData","Added Movie: $movieTitle")
    }
    // loop To Calculate Average
    fun calculateAverageRating(): Double {
        if (ratings.isEmpty()) {
            return 0.0
        }
        var total = 0
        //      Loop for all Ratings
        for (rating in ratings)
        { total += rating }
        // Calculate Average
        val average = total.toDouble() / ratings.size
        Log.i("MovieData", "Average Rating: $average")
        return average
    }
}
