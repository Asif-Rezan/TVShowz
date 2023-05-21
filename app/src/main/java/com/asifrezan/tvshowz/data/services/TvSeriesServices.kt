package com.asifrezan.tvshowz.data.services
import com.asifrezan.tvshowz.data.models.movies.Details
import com.asifrezan.tvshowz.data.models.movies.Movies
import com.asifrezan.tvshowz.data.models.tv_series.TvSeries
import com.asifrezan.tvshowz.data.models.tv_series.TvSeriseDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TvSeriesServices {

    @GET("$API_URL/$VERSION_3_URL/$CATEGORY_TV/$TYPE_POPULAR")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getTvSeries(
        @Query("page") page: Int
    ): Response<TvSeries>


    @GET("$API_URL/$VERSION_3_URL/$CATEGORY_TV/{series_id}")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getTvSeriesDetails(
        @Path("series_id") series_id: Int,
    ): Response<TvSeriseDetails>



    @GET("$API_URL/$VERSION_3_URL/$CATEGORY_TV/{series_id}/$SIMILAR")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getSimilarTvSeries(
        @Path("series_id") series_id: Int,
    ): Response<TvSeries>


}