package com.asifrezan.tvshowz.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asifrezan.tvshowz.R
import com.asifrezan.tvshowz.databinding.ActivityDetailsBinding
import com.asifrezan.tvshowz.data.repository.MoviesRepository
import com.asifrezan.tvshowz.data.services.MovieServices
import com.asifrezan.tvshowz.data.services.RetrofitHelper
import com.asifrezan.tvshowz.ui.viewmodels.MovieViewModel
import com.asifrezan.tvshowz.ui.viewmodels.factory.MovieViewModelFactory
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       // setContentView(R.layout.activity_details)


        val clickedMovieId = intent.getStringExtra("movieId")

      //  Log.e("ddddddddddddddddd",clickedMovieId.toInt())





        val movieServices = RetrofitHelper.getInstense().create(MovieServices::class.java)
        val repository = MoviesRepository(movieServices)
        val viewModelFactory = MovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)


        movieViewModel.movieDetails.observe(this, Observer { movies ->
            // Handle the movie data here
            Log.e("rrr", movies.title.toString())

            binding.movieTitle.setText(movies.title.toString())
            binding.rating.setText(movies.vote_average.toString())
            binding.releaseDate.setText(movies.release_date.toString())

            val genreList = movies.genres.map { it.name }
            val genreString = genreList.joinToString(separator = " | ")
            binding.genre.text = genreString


            binding.overview.setText(movies.overview)

            val posterUrl = "https://image.tmdb.org/t/p/w500/${movies.poster_path}"

            Glide.with(this)
                .load(posterUrl)
                .placeholder(R.drawable.loading)
                .into(binding.imageView)


        })


        if (clickedMovieId != null) {
            movieViewModel.getMovieDetails(clickedMovieId.toInt())
        }










    }
}