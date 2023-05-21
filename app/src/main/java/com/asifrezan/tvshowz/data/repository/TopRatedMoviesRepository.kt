package com.asifrezan.tvshowz.data.repository

import com.asifrezan.tvshowz.data.models.movies.Details
import com.asifrezan.tvshowz.data.models.movies.Movies
import com.asifrezan.tvshowz.data.services.TopRatedMoviesServices

class TopRatedMoviesRepository(private val topRatedMoviesServices: TopRatedMoviesServices) {

    suspend fun getMovies(page : Int): Movies? {
        val response = topRatedMoviesServices.getMovies(page)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getDetails(movieId: Int): Details? {
        val response = topRatedMoviesServices.getDetails(movieId)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getSimilarMovie(movieId: Int): Movies? {
        val response = topRatedMoviesServices.getSimilarMovies(movieId)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }


}