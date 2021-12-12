package com.example.myapplication.precentation.coinlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.remote.CoinDtoX

@Composable
fun CoinItems(coin: CoinDtoX,
              onItemClick:()->Unit
              ) {

    Card(elevation = 3.dp, modifier = Modifier
        .clip(RoundedCornerShape(2))
        .padding(4.dp)
        .clickable {
            onItemClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = coin.name + " ${coin.symbol}", style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Text(
                text = coin.rank?.toString() ?: "", style = TextStyle(
                    fontSize = 14.sp
                )
            )


        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
    }


}

