package com.example.myapplication.domain.repository

import android.util.Log
import com.example.myapplication.data.model.CoinInfo
import com.example.myapplication.data.model.MovieListResponse
import com.example.myapplication.data.remote.ApiService
import com.example.myapplication.data.remote.CoinDtoX
import com.example.myapplication.data.remote.MovieApi
import com.example.myapplication.data.remote.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinAppRepository @Inject constructor (private  val apiService: ApiService
                                             ) {

    fun getCoinList() = flow<Resource<List<CoinDtoX>>> {
      try {
         emit(Resource.Loading())
         val coinList = apiService.getCurrencyList()
         emit(Resource.Success<List<CoinDtoX>>(coinList))

      } catch (e: HttpException) {
         emit(
            Resource.Error<List<CoinDtoX>>(
               e.localizedMessage ?: "An unexpected error occured"
            )
         )
      } catch (e: IOException) {
         emit(Resource.Error<List<CoinDtoX>>("Couldn't reach server. Check your internet connection."))
      }


   }

    fun getCoinDetail(id:String) = flow<Resource<CoinInfo>> {
        try {
            emit(Resource.Loading())
            val coinList = apiService.getCurrencyDetail(id)
            Log.e("coin detail:-","$coinList")
            emit(Resource.Success<CoinInfo>(coinList))

        } catch (e: HttpException) {
            emit(
                Resource.Error<CoinInfo>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<CoinInfo>("Couldn't reach server. Check your internet connection."))
        }


    }



}