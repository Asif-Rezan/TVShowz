package com.asifrezan.tvshowz.ui.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asifrezan.tvshowz.data.repository.TopRatedTvRepository
import com.asifrezan.tvshowz.data.repository.TvSeriesRepository
import com.asifrezan.tvshowz.ui.viewmodels.TopTvSeriesViewModel
import com.asifrezan.tvshowz.ui.viewmodels.TvSeriesViewModel

class TopRatedTvViewModelFactory(private val topRatedTvRepository: TopRatedTvRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopTvSeriesViewModel::class.java)) {
            return TopTvSeriesViewModel(topRatedTvRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}