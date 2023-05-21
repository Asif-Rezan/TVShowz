package com.asifrezan.tvshowz.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asifrezan.tvshowz.R
import com.asifrezan.tvshowz.data.models.movies.MovieList
import com.asifrezan.tvshowz.data.repository.MoviesRepository
import com.asifrezan.tvshowz.data.services.MovieServices
import com.asifrezan.tvshowz.data.services.RetrofitHelper
import com.asifrezan.tvshowz.databinding.ActivityDetailsBinding
import com.asifrezan.tvshowz.ui.adapters.MoviesListAdapter
import com.asifrezan.tvshowz.ui.viewmodels.MovieViewModel
import com.asifrezan.tvshowz.ui.viewmodels.factory.MovieViewModelFactory
import com.asifrezan.tvshowz.utils.IMAGE_STORAGE_URL
import com.bumptech.glide.Glide


class DetailsActivity : AppCompatActivity() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var moviesGridView: GridView
    val movieList = mutableListOf<MovieList>()



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

        //API call
        if (clickedMovieId != null) {
            movieViewModel.getMovieDetails(clickedMovieId.toInt())
            movieViewModel.getSimilarMovies(clickedMovieId.toInt())
        }




        //Display the movie details
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

            val posterUrl = "$IMAGE_STORAGE_URL/${movies.poster_path}"

            Glide.with(this)
                .load(posterUrl)
                .placeholder(R.drawable.loading)
                .into(binding.imageView)

        })



        //Display similar movies

        moviesGridView = binding.gridView

        val movieAdapter = MoviesListAdapter(movieList=movieList, context = this)


        movieViewModel.similar_movies.observe(this, Observer {

            if(it.results.isEmpty())
            {
                binding.similarItemCheck.setText("No Similar Item Available")
                binding.similarItemCheck.visibility = View.VISIBLE
            }



            Log.e("xxxxx", it.results.toString())
            movieList.addAll(it.results)
            movieAdapter.notifyDataSetChanged()

            moviesGridView.numColumns = movieList.size

        })

        moviesGridView.adapter = movieAdapter
        moviesGridView.isHorizontalScrollBarEnabled = true
















    }
}