package com.asifrezan.tvshowz.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asifrezan.tvshowz.data.models.movies.Details
import com.asifrezan.tvshowz.data.models.movies.Movies
import com.asifrezan.tvshowz.data.repository.TopRatedMoviesRepository
import kotlinx.coroutines.launch

class TopRatedMovieViewModel(private val topRatedMoviesRepository: TopRatedMoviesRepository):ViewModel() {

    private val _movies = MutableLiveData<Movies>()
    val movies: LiveData<Movies> = _movies

    private val _movieDetails = MutableLiveData<Details>()
    val movieDetails: LiveData<Details> = _movieDetails

    private val _similarMovies = MutableLiveData<Movies>()
    val similar_movies = _similarMovies

    fun getMovies(page:Int) {
        viewModelScope.launch {
            Log.e("eee","viewmodel")
            try {
                val movies = topRatedMoviesRepository.getMovies(page)
                _movies.value = movies!!
            } catch (e: Exception) {
                Log.e("eee",e.message.toString())

            }
        }
    }

    fun getMovieDetails(movieId : Int) {
        viewModelScope.launch {
            Log.e("eee","viewmodel")
            try {
                val movieDetails = topRatedMoviesRepository.getDetails(movieId)
                _movieDetails.value = movieDetails!!
            } catch (e: Exception) {
                Log.e("eee",e.message.toString())

            }
        }
    }

    fun getSimilarMovies(movieId: Int) {
        viewModelScope.launch {
            Log.e("eee","viewmodel")
            try {
                val movies = topRatedMoviesRepository.getSimilarMovie(movieId)
                _similarMovies.value = movies!!
            } catch (e: Exception) {
                Log.e("eee",e.message.toString())

            }
        }
    }
}