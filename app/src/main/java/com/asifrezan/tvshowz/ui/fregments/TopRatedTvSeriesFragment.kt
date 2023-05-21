package com.asifrezan.tvshowz.ui.fregments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.asifrezan.tvshowz.R
import com.asifrezan.tvshowz.data.models.movies.MovieList
import com.asifrezan.tvshowz.data.models.tv_series.TvSeries
import com.asifrezan.tvshowz.data.models.tv_series.TvSeriesList
import com.asifrezan.tvshowz.data.repository.MoviesRepository
import com.asifrezan.tvshowz.data.repository.TopRatedTvRepository
import com.asifrezan.tvshowz.data.repository.TvSeriesRepository
import com.asifrezan.tvshowz.data.services.MovieServices
import com.asifrezan.tvshowz.data.services.RetrofitHelper
import com.asifrezan.tvshowz.data.services.TopRatedTvSeriesServices
import com.asifrezan.tvshowz.data.services.TvSeriesServices
import com.asifrezan.tvshowz.databinding.FragmentMoviesBinding
import com.asifrezan.tvshowz.databinding.FragmentTopRatedTvSeriesBinding
import com.asifrezan.tvshowz.databinding.FragmentTvSeriesBinding
import com.asifrezan.tvshowz.ui.adapters.MoviesListAdapter
import com.asifrezan.tvshowz.ui.adapters.TvSeriseListAdapter
import com.asifrezan.tvshowz.ui.viewmodels.MovieViewModel
import com.asifrezan.tvshowz.ui.viewmodels.TopTvSeriesViewModel
import com.asifrezan.tvshowz.ui.viewmodels.TvSeriesViewModel
import com.asifrezan.tvshowz.ui.viewmodels.factory.MovieViewModelFactory
import com.asifrezan.tvshowz.ui.viewmodels.factory.TopRatedTvViewModelFactory
import com.asifrezan.tvshowz.ui.viewmodels.factory.TvSeriseViewModelFactory


class TopRatedTvSeriesFragment : Fragment() {

    private lateinit var binding: FragmentTopRatedTvSeriesBinding
    private lateinit var tvSeriseGridView: GridView
    val tvSeriseList = mutableListOf<TvSeriesList>()
    private lateinit var TopRatedtvSeriseViewModel: TopTvSeriesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRatedTvSeriesBinding.inflate(inflater, container, false)
        val view = binding.root
        tvSeriseGridView = binding.gridViewId

        val movieAdapter = TvSeriseListAdapter(requireContext(), tvSeriseList)


        val topRatedtvSeriseServices = RetrofitHelper.getInstense().create(TopRatedTvSeriesServices::class.java)
        val repository = TopRatedTvRepository(topRatedtvSeriseServices)
        val viewModelFactory = TopRatedTvViewModelFactory(repository)
        TopRatedtvSeriseViewModel = ViewModelProvider(this, viewModelFactory).get(TopTvSeriesViewModel::class.java)



        // Observe the tv Serise LiveData
        TopRatedtvSeriseViewModel.tvSeries.observe(viewLifecycleOwner, Observer { tvSerise ->

            Log.e("rrr", tvSerise.results.toString())
            tvSeriseList.addAll(tvSerise.results)
            movieAdapter.notifyDataSetChanged()

        })


        TopRatedtvSeriseViewModel.getTvSeries(1)

        tvSeriseGridView.adapter = movieAdapter

        return view
    }








}