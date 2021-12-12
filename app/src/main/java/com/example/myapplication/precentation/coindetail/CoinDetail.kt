package com.example.myapplication.precentation.coindetail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.remote.CoinDtoX
import com.example.myapplication.precentation.coinlist.CoinListViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.google.gson.Gson
import kotlinx.coroutines.selects.whileSelect

@Composable
fun CoinDetail(navController: NavController) {

    val viewModel: CoinListViewModel = hiltViewModel()

    val coinData = viewModel.gsonArg

    val scaffoldState = rememberScaffoldState()
    var coinDetail = viewModel.stateDetail.value

    LaunchedEffect(key1 = true) {
        val coin = Gson().fromJson(coinData, CoinDtoX::class.java)
        viewModel.getCoinData(coin.id!!)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar() {

                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Spacer(modifier = Modifier.width(20.dp))

                Row() {
                    Text(text = "${Gson().fromJson(coinData, CoinDtoX::class.java).name}")
                }

            }

        }

    ) {

        if (coinData != "-1") {

            val coin = Gson().fromJson(coinData, CoinDtoX::class.java)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Text(text = coin.name ?: "")
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                Text(text = coin.symbol ?: "")
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                Text(text = coin.type ?: "")
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                Text(
                    text = if (coin.is_active!!) {
                        "Active"
                    } else {
                        "Disable"
                    }
                )
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                Text(text = coin.name ?: "")
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                Text(text = coin.id ?: "")
                Spacer(modifier = Modifier.height(32.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(), mainAxisSpacing = 10.dp,
                    crossAxisSpacing = 10.dp
                ) {

                    coinDetail?.coins?.tags?.forEach { tag ->

                        TagItems(tag.name ?: "")
                    }
                }


            }
        } else {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(text = "Not found")
            }
        }
    }


    LaunchedEffect(key1 = true, block = {

    })


}