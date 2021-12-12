package com.example.myapplication.precentation.coinlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navArgument
import com.example.myapplication.data.remote.CoinDtoX
import com.google.gson.Gson

@Composable
fun CoinList(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {


    var coinlist = viewModel.state.value

    val editTextState = remember {
        mutableStateOf(TextFieldValue(""))
    }



    LaunchedEffect(key1 = true, block = {

    })

    Column(modifier = Modifier.fillMaxSize()) {

        SearchView(editTextState, onChanged = {
            // todo search here

            searchItem(coinlist, it)
        })

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            if (coinlist.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    val filteredList: List<CoinDtoX> = if (editTextState.value.text.isBlank()) {
                        coinlist.coins
                    } else {
                        val tempList = mutableListOf<CoinDtoX>()
                        for (item in coinlist.coins){
                            if (item.name!!.toLowerCase().contains(editTextState.value.text.toLowerCase()))
                            {
                               tempList.add(item)
                            }
                        }
                        tempList
                    }

                    items(
                        filteredList
                    ) { coin ->
                        val gsonString = Gson().toJson(coin)

                        CoinItems(coin = coin, onItemClick = {
                            navController.navigate("list_detail" + "/${gsonString}")
                        })
                    }


                }
            }


        }
    }


}

fun searchItem(coinListState: CoinListState, it: String) {

    if (it.isBlank()) {
        coinListState.searchList = coinListState.coins
        return
    }
    for (item in coinListState.coins) {
        if (it.toLowerCase().trim().contains(item.name!!.toLowerCase()!!.trim())) {
            coinListState.searchList.toMutableList().add(item)
        } else {

        }
    }
}

