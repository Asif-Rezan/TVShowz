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
import com.asifrezan.tvshowz.data.repository.MoviesRepository
import com.asifrezan.tvshowz.data.repository.TopRatedMoviesRepository
import com.asifrezan.tvshowz.data.services.MovieServices
import com.asifrezan.tvshowz.data.services.RetrofitHelper
import com.asifrezan.tvshowz.data.services.TopRatedMoviesServices
import com.asifrezan.tvshowz.databinding.FragmentMoviesBinding
import com.asifrezan.tvshowz.databinding.FragmentTopRatedMovieBinding
import com.asifrezan.tvshowz.ui.adapters.MoviesListAdapter
import com.asifrezan.tvshowz.ui.viewmodels.MovieViewModel
import com.asifrezan.tvshowz.ui.viewmodels.TopRatedMovieViewModel
import com.asifrezan.tvshowz.ui.viewmodels.factory.MovieViewModelFactory
import com.asifrezan.tvshowz.ui.viewmodels.factory.TopRatedMovieViewModelFactory


class TopRatedMovieFragment : Fragment() {

    private lateinit var binding: FragmentTopRatedMovieBinding
    private lateinit var moviesGridView: GridView
    val movieList = mutableListOf<MovieList>()
    private lateinit var movieViewModel: TopRatedMovieViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopRatedMovieBinding.inflate(inflater, container, false)
        val view = binding.root

        moviesGridView = binding.gridViewId

        val movieAdapter = MoviesListAdapter(movieList=movieList, context = requireContext())


        val topRatedmovieServices = RetrofitHelper.getInstense().create(TopRatedMoviesServices::class.java)
        val repository = TopRatedMoviesRepository(topRatedmovieServices)
        val viewModelFactory = TopRatedMovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this, viewModelFactory).get(TopRatedMovieViewModel::class.java)



        // Observe the movies LiveData
        movieViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            // Handle the movie data here
            Log.e("rrr", movies.results.toString())
            movieList.addAll(movies.results)
            movieAdapter.notifyDataSetChanged()

        })

        // Call the getMovies() function to fetch the data
        movieViewModel.getMovies(2)

        moviesGridView.adapter = movieAdapter

        return view
    }






}