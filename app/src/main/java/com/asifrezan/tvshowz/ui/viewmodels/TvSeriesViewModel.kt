package com.asifrezan.tvshowz.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asifrezan.tvshowz.data.models.tv_series.TvSeries
import com.asifrezan.tvshowz.data.repository.TvSeriesRepository
import kotlinx.coroutines.launch

class TvSeriesViewModel(private val tvSeriesRepository: TvSeriesRepository) : ViewModel() {
    private val _tvSeries = MutableLiveData<TvSeries>()
    val tvSeries: LiveData<TvSeries> = _tvSeries


    fun getTvSeries(page:Int) {
        viewModelScope.launch {
            Log.e("eee","viewmodel")
            try {
                val tv_series = tvSeriesRepository.getTvSeries(page)
                _tvSeries.value = tv_series!!
            } catch (e: Exception) {
                Log.e("eee",e.message.toString())

            }
        }
    }



}