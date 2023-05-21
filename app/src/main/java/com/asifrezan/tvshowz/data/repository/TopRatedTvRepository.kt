package com.asifrezan.tvshowz.data.repository

import com.asifrezan.tvshowz.data.models.tv_series.TvSeries
import com.asifrezan.tvshowz.data.models.tv_series.TvSeriseDetails
import com.asifrezan.tvshowz.data.services.TopRatedTvSeriesServices

class TopRatedTvRepository(private val topRatedTvSeriesServices: TopRatedTvSeriesServices) {

    suspend fun getTvSeries(page : Int): TvSeries? {
        val response = topRatedTvSeriesServices.getTvSeries(page)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

//    suspend fun getTvSeriesDetails(series_id: Int): TvSeriseDetails? {
//        val response = topRatedTvSeriesServices.getTvSeriesDetails(series_id)
//        if (response.isSuccessful) {
//            return response.body()
//        }
//        return null
//    }

//    suspend fun getSimilarTvSeries(series_id: Int): TvSeries? {
//        val response = topRatedTvSeriesServices.getSimilarTvSeries(series_id)
//        if (response.isSuccessful) {
//            return response.body()
//        }
//        return null
//    }
}