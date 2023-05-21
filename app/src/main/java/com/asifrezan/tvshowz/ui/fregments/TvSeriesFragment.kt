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
import com.asifrezan.tvshowz.data.repository.TvSeriesRepository
import com.asifrezan.tvshowz.data.services.MovieServices
import com.asifrezan.tvshowz.data.services.RetrofitHelper
import com.asifrezan.tvshowz.data.services.TvSeriesServices
import com.asifrezan.tvshowz.databinding.FragmentMoviesBinding
import com.asifrezan.tvshowz.databinding.FragmentTvSeriesBinding
import com.asifrezan.tvshowz.ui.adapters.MoviesListAdapter
import com.asifrezan.tvshowz.ui.adapters.TvSeriseListAdapter
import com.asifrezan.tvshowz.ui.viewmodels.MovieViewModel
import com.asifrezan.tvshowz.ui.viewmodels.TvSeriesViewModel
import com.asifrezan.tvshowz.ui.viewmodels.factory.MovieViewModelFactory
import com.asifrezan.tvshowz.ui.viewmodels.factory.TvSeriseViewModelFactory


class TvSeriesFragment : Fragment() {

    private lateinit var binding: FragmentTvSeriesBinding
    private lateinit var tvSeriseGridView: GridView
    val tvSeriseList = mutableListOf<TvSeriesList>()
    private lateinit var tvSeriseViewModel: TvSeriesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvSeriesBinding.inflate(inflater, container, false)
        val view = binding.root
        tvSeriseGridView = binding.gridViewId

        val movieAdapter = TvSeriseListAdapter(requireContext(), tvSeriseList)


        val tvSeriseServices = RetrofitHelper.getInstense().create(TvSeriesServices::class.java)
        val repository = TvSeriesRepository(tvSeriseServices)
        val viewModelFactory = TvSeriseViewModelFactory(repository)
        tvSeriseViewModel = ViewModelProvider(this, viewModelFactory).get(TvSeriesViewModel::class.java)



        // Observe the tv Serise LiveData
        tvSeriseViewModel.tvSeries.observe(viewLifecycleOwner, Observer { tvSerise ->

            Log.e("rrr", tvSerise.results.toString())
            tvSeriseList.addAll(tvSerise.results)
            movieAdapter.notifyDataSetChanged()

        })


        tvSeriseViewModel.getTvSeries(1)

        tvSeriseGridView.adapter = movieAdapter

        return view
    }








}