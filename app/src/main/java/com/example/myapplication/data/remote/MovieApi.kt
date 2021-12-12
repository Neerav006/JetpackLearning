package com.example.myapplication.data.remote

import com.example.myapplication.BuildConfig
import com.example.myapplication.common.AppConstants
import com.example.myapplication.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("3/movie/popular")
    suspend fun getMovieList(
        @Query("page") page: String,
        @Query("api_key") api_key: String = BuildConfig.TMDB_API_KEY
    ):MovieListResponse
}