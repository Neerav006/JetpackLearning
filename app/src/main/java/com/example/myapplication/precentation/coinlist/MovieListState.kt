package com.example.myapplication.precentation.coinlist

import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.model.MovieListResponse
import com.example.myapplication.data.remote.CoinDtoX

data class MovieListState(

    val isLoading: Boolean = false,
    val movies: MovieListResponse? = null,
    val error: String = "",
    var searchList:List<Movie> = emptyList()
) {
}