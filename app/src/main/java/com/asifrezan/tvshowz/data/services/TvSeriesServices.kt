package com.asifrezan.tvshowz.data.services
import com.asifrezan.tvshowz.data.models.tv_series.TvSeries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TvSeriesServices {

    @GET("$API_URL/$VERSION_3_URL/$CATEGORY_TV/$TYPE_POPULAR")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getTvSeries(
        @Query("page") page: Int
    ): Response<TvSeries>


}