package com.example.myapplication.precentation.coindetail

import com.example.myapplication.data.model.CoinInfo
import com.example.myapplication.data.model.MovieListResponse
import com.example.myapplication.data.remote.CoinDtoX

data class CoinDetailsState(

    val isLoading: Boolean = false,
    val coins : CoinInfo? = null,
    val error: String = ""
)  {
}

data class MovieListState(
    val isLoading: Boolean = false,
    val movies : MovieListResponse? = null,
    val error: String = ""

){

}