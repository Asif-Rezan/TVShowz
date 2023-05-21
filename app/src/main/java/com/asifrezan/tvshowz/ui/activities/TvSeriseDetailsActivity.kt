package com.asifrezan.tvshowz.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asifrezan.tvshowz.R
import com.asifrezan.tvshowz.data.models.movies.MovieList
import com.asifrezan.tvshowz.data.models.tv_series.TvSeriesList
import com.asifrezan.tvshowz.data.repository.MoviesRepository
import com.asifrezan.tvshowz.data.repository.TvSeriesRepository
import com.asifrezan.tvshowz.data.services.MovieServices
import com.asifrezan.tvshowz.data.services.RetrofitHelper
import com.asifrezan.tvshowz.data.services.TvSeriesServices
import com.asifrezan.tvshowz.databinding.ActivityDetailsBinding
import com.asifrezan.tvshowz.databinding.ActivityTvSeriseDetailsBinding
import com.asifrezan.tvshowz.ui.adapters.MoviesListAdapter
import com.asifrezan.tvshowz.ui.adapters.TvSeriseListAdapter
import com.asifrezan.tvshowz.ui.viewmodels.MovieViewModel
import com.asifrezan.tvshowz.ui.viewmodels.TvSeriesViewModel
import com.asifrezan.tvshowz.ui.viewmodels.factory.MovieViewModelFactory
import com.asifrezan.tvshowz.ui.viewmodels.factory.TvSeriseViewModelFactory
import com.asifrezan.tvshowz.utils.IMAGE_STORAGE_URL
import com.bumptech.glide.Glide

class TvSeriseDetailsActivity : AppCompatActivity() {
    private lateinit var tvSeriesViewModel: TvSeriesViewModel
    private lateinit var binding: ActivityTvSeriseDetailsBinding
    private lateinit var tvSeriesGridView: GridView
    val tvSeriesList = mutableListOf<TvSeriesList>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTvSeriseDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // setContentView(R.layout.activity_details)


        val clickedTvSeriseId = intent.getStringExtra("serise_id")

        //  Log.e("ddddddddddddddddd",clickedMovieId.toInt())

        val tvSeriesServices = RetrofitHelper.getInstense().create(TvSeriesServices::class.java)
        val repository = TvSeriesRepository(tvSeriesServices)
        val viewModelFactory = TvSeriseViewModelFactory(repository)
        tvSeriesViewModel = ViewModelProvider(this, viewModelFactory).get(TvSeriesViewModel::class.java)

        //API call
        if (clickedTvSeriseId != null) {
            tvSeriesViewModel.getTvSeriesDetails(clickedTvSeriseId.toInt())
            tvSeriesViewModel.getSimilarTvSeries(clickedTvSeriseId.toInt())
        }




        //Display the series details
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



        //Display similar tv series

        tvSeriesGridView = binding.gridView

        val movieAdapter = TvSeriseListAdapter(this, tvSeriesList)


        tvSeriesViewModel.similarTvSeries.observe(this, Observer {

            if(it.results.isEmpty())
            {
                binding.similarItemCheck.setText("No Similar Item Available")
                binding.similarItemCheck.visibility = View.VISIBLE
            }

            Log.e("xxxxx", it.results.toString())
            tvSeriesList.addAll(it.results)
            movieAdapter.notifyDataSetChanged()

            tvSeriesGridView.numColumns = tvSeriesList.size

        })

        tvSeriesGridView.adapter = movieAdapter
        tvSeriesGridView.isHorizontalScrollBarEnabled = true
















    }
}