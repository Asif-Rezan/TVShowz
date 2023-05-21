package com.asifrezan.tvshowz.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asifrezan.tvshowz.data.repository.TopRatedMoviesRepository
import com.asifrezan.tvshowz.ui.viewmodels.TopRatedMovieViewModel

class TopRatedMovieViewModelFactory(private val topRatedMoviesRepository: TopRatedMoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopRatedMovieViewModel::class.java)) {
            return TopRatedMovieViewModel(topRatedMoviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
