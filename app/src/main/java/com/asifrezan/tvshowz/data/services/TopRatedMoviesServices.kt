package com.asifrezan.tvshowz.data.services

import com.asifrezan.tvshowz.data.models.movies.Details
import com.asifrezan.tvshowz.data.models.movies.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TopRatedMoviesServices {


    @GET("$API_URL/$VERSION_3_URL/$CATEGORY_MOVIE/$TYPE_TOP_RATED")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getMovies(
        @Query("page") page: Int
    ): Response<Movies>



    @GET("$API_URL/$VERSION_3_URL/$CATEGORY_MOVIE/{movie_id}")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getDetails(
        @Path("movie_id") movie_id: Int,
    ): Response<Details>


    @GET("$API_URL/$VERSION_3_URL/$CATEGORY_MOVIE/{movie_id}/$SIMILAR")
    @Headers("Authorization: Bearer $TOKEN")
    suspend fun getSimilarMovies(
        @Path("movie_id") movie_id: Int,
    ): Response<Movies>
}