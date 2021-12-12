package com.example.myapplication.domain.repository

import android.util.Log
import com.example.myapplication.data.model.MovieListResponse
import com.example.myapplication.data.remote.MovieApi
import com.example.myapplication.data.remote.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MovieRepository @Inject constructor(private val movieApi: MovieApi)  {

    suspend fun getMovieList(page:String) :MovieListResponse {

       return  movieApi.getMovieList(page = page)

    }

}