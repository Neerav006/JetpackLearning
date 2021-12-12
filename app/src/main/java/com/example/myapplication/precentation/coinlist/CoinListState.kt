package com.example.myapplication.precentation.coinlist

import com.example.myapplication.data.remote.CoinDtoX

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinDtoX> = emptyList(),
    val error: String = "",
    var searchList:List<CoinDtoX> = emptyList()
)