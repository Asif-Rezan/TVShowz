package com.asifrezan.tvshowz.data.repository

import com.asifrezan.tvshowz.data.models.movies.Details
import com.asifrezan.tvshowz.data.models.tv_series.TvSeries
import com.asifrezan.tvshowz.data.models.tv_series.TvSeriseDetails
import com.asifrezan.tvshowz.data.services.TvSeriesServices

class TvSeriesRepository(private val tvSeriesServices : TvSeriesServices) {

    suspend fun getTvSeries(page : Int): TvSeries? {
        val response = tvSeriesServices.getTvSeries(page)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getTvSeriesDetails(series_id: Int): TvSeriseDetails? {
        val response = tvSeriesServices.getTvSeriesDetails(series_id)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}