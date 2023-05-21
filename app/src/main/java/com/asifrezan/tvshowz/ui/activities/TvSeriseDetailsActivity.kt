package com.asifrezan.tvshowz.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asifrezan.tvshowz.R
import com.asifrezan.tvshowz.data.models.movies.MovieList
import com.asifrezan.tvshowz.data.repository.MoviesRepository
import com.asifrezan.tvshowz.data.repository.TvSeriesRepository
import com.asifrezan.tvshowz.data.services.MovieServices
import com.asifrezan.tvshowz.data.services.RetrofitHelper
import com.asifrezan.tvshowz.data.services.TvSeriesServices
import com.asifrezan.tvshowz.databinding.ActivityDetailsBinding
import com.asifrezan.tvshowz.databinding.ActivityTvSeriseDetailsBinding
import com.asifrezan.tvshowz.ui.adapters.MoviesListAdapter
import com.asifrezan.tvshowz.ui.viewmodels.MovieViewModel
import com.asifrezan.tvshowz.ui.viewmodels.TvSeriesViewModel
import com.asifrezan.tvshowz.ui.viewmodels.factory.MovieViewModelFactory
import com.asifrezan.tvshowz.ui.viewmodels.factory.TvSeriseViewModelFactory
import com.asifrezan.tvshowz.utils.IMAGE_STORAGE_URL
import com.bumptech.glide.Glide

class TvSeriseDetailsActivity : AppCompatActivity() {
    private lateinit var tvSeriesViewModel: TvSeriesViewModel
    private lateinit var binding: ActivityTvSeriseDetailsBinding
    private lateinit var moviesGridView: GridView
    val movieList = mutableListOf<MovieList>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvSeriseDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // setContentView(R.layout.activity_details)


        val clickedMovieId = intent.getStringExtra("serise_id")

        //  Log.e("ddddddddddddddddd",clickedMovieId.toInt())

        val tvSeriesServices = RetrofitHelper.getInstense().create(TvSeriesServices::class.java)
        val repository = TvSeriesRepository(tvSeriesServices)
        val viewModelFactory = TvSeriseViewModelFactory(repository)
        tvSeriesViewModel = ViewModelProvider(this, viewModelFactory).get(TvSeriesViewModel::class.java)

        //API call
        if (clickedMovieId != null) {
            tvSeriesViewModel.getTvSeriesDetails(clickedMovieId.toInt())
            //tvSeriesViewModel.getSimilarMovies(clickedMovieId.toInt())
        }




        //Display the movie details
        tvSeriesViewModel.tvSeriesDetails.observe(this, Observer { tvSeries ->
            // Handle the movie data here
            Log.e("rrr", tvSeries.toString())

            binding.movieTitle.setText(tvSeries.name.toString())
            binding.rating.setText(tvSeries.vote_average.toString())
            binding.releaseDate.setText(tvSeries.first_air_date.toString())

            val genreList = tvSeries.genres.map { it.name }
            val genreString = genreList.joinToString(separator = " | ")
            binding.genre.text = genreString


            binding.overview.setText(tvSeries.overview)

            val posterUrl = "$IMAGE_STORAGE_URL/${tvSeries.poster_path}"

            Glide.with(this)
                .load(posterUrl)
                .placeholder(R.drawable.loading)
                .into(binding.imageView)

        })



        //Display similar movies

//        moviesGridView = binding.gridView
//
//        val movieAdapter = MoviesListAdapter(movieList=movieList, context = this)
//
//
//        movieViewModel.similar_movies.observe(this, Observer {
//
//            Log.e("xxxxx", it.results.toString())
//            movieList.addAll(it.results)
//            movieAdapter.notifyDataSetChanged()
//
//            moviesGridView.numColumns = movieList.size
//
//        })
//
//        moviesGridView.adapter = movieAdapter
//        moviesGridView.isHorizontalScrollBarEnabled = true
















    }
}