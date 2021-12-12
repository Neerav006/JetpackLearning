package com.example.myapplication.precentation.coinlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.model.MovieListResponse
import com.example.myapplication.data.remote.CoinDtoX
import com.example.myapplication.data.remote.Resource
import com.example.myapplication.data.remote.Resource.*
import com.example.myapplication.data.repository.paged.MovieSource
import com.example.myapplication.domain.repository.CoinAppRepository
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.precentation.coindetail.CoinDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinAppRepository: CoinAppRepository,
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository
) :
    ViewModel() {
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    private val _stateDetail = mutableStateOf(CoinDetailsState())
    val stateDetail: State<CoinDetailsState> = _stateDetail

    private val _movieListState = mutableStateOf(MovieListState())
    val movieListState: State<MovieListState> = _movieListState


    lateinit var gsonArg: String

    init {
      //  getCoinData()
        gsonArg = savedStateHandle.get<String>("data") ?: "-1"
    }

    // Get coin list from that
    fun getCoinData() {
        coinAppRepository.getCoinList().onEach { coinDtx ->
            when (coinDtx) {
                is Error -> {
                    _state.value = CoinListState(error = coinDtx.message ?: "Unexpected error")
                }
                is Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
                is Success -> {
                    _state.value = CoinListState(coins = coinDtx.data!!)
                }
            }

        }.launchIn(viewModelScope)

    }

    // coin detail
    fun getCoinData(id: String) {
        coinAppRepository.getCoinDetail(id).onEach { coinDtx ->
            when (coinDtx) {
                is Error -> {
                    _stateDetail.value =
                        CoinDetailsState(error = coinDtx.message ?: "Unexpected error")
                }
                is Loading -> {
                    _stateDetail.value = CoinDetailsState(isLoading = true)
                }
                is Success -> {
                    _stateDetail.value = CoinDetailsState(coins = coinDtx.data!!)
                }
            }

        }.launchIn(viewModelScope)

    }

    val movies: Flow<PagingData<Movie>> = Pager(PagingConfig(pageSize = 20)) {
        MovieSource(movieRepository)
    }.flow



}