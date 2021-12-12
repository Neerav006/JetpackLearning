package com.example.myapplication.data.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CoinDtoX(
    val id: String? = "1",
    val is_active: Boolean? = true,
    val is_new: Boolean? = true,
    val name: String? = "BTC",
    val rank: Int?= 1,
    val symbol: String? = "$",
    val type: String? = "Btc"
):Parcelable