package com.asifrezan.tvshowz.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asifrezan.tvshowz.data.repository.MoviesRepository
import com.asifrezan.tvshowz.ui.viewmodels.MovieViewModel


class MovieViewModelFactory(private val moviesRepository: MoviesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
