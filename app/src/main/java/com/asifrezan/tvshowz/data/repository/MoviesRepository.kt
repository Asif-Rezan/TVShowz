package com.asifrezan.tvshowz.data.repository

import com.asifrezan.tvshowz.data.models.movies.Details
import com.asifrezan.tvshowz.data.models.movies.Movies
import com.asifrezan.tvshowz.data.services.MovieServices

class MoviesRepository(private val movieServices: MovieServices) {
     suspend fun getMovies(page : Int): Movies? {
        val response = movieServices.getMovies(page)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getDetails(movieId: Int): Details? {
        val response = movieServices.getDetails(movieId)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}