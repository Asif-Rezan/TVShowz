package com.asifrezan.tvshowz.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asifrezan.tvshowz.data.models.tv_series.TvSeries
import com.asifrezan.tvshowz.data.models.tv_series.TvSeriseDetails
import com.asifrezan.tvshowz.data.repository.TopRatedTvRepository
import kotlinx.coroutines.launch

class TopTvSeriesViewModel(private val topRatedTvRepository: TopRatedTvRepository) : ViewModel() {

    private val _tvSeries = MutableLiveData<TvSeries>()
    val tvSeries: LiveData<TvSeries> = _tvSeries

//    private val _tvSeriesDetails = MutableLiveData<TvSeriseDetails>()
//    val tvSeriesDetails: LiveData<TvSeriseDetails> = _tvSeriesDetails
//
//    private val _similarTvSeries = MutableLiveData<TvSeries>()
//    val similarTvSeries: LiveData<TvSeries> = _similarTvSeries


    fun getTvSeries(page:Int) {
        viewModelScope.launch {
            Log.e("eee","viewmodel")
            try {
                val tv_series = topRatedTvRepository.getTvSeries(page)
                _tvSeries.value = tv_series!!
            } catch (e: Exception) {
                Log.e("eee",e.message.toString())

            }
        }
    }

//    fun getTvSeriesDetails(serise_id : Int) {
//        viewModelScope.launch {
//            Log.e("eee","viewmodel")
//            try {
//                val tvSeriseDetails = topRatedTvRepository.getTvSeriesDetails(serise_id)
//                _tvSeriesDetails.value = tvSeriseDetails!!
//            } catch (e: Exception) {
//                Log.e("eee",e.message.toString())
//
//            }
//        }
//    }

//    fun getSimilarTvSeries(serise_id: Int) {
//        viewModelScope.launch {
//            Log.e("eee","viewmodel")
//            try {
//                val similar_tv_series = topRatedTvRepository.getSimilarTvSeries(serise_id)
//                _similarTvSeries.value = similar_tv_series!!
//            } catch (e: Exception) {
//                Log.e("eee",e.message.toString())
//
//            }
//        }
//    }

}