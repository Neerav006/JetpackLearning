package com.example.myapplication.precentation.coindetail

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun  TagItems(tagName:String) {

    Box(modifier = Modifier
        .background(
            color = androidx.compose.ui.graphics.Color.Magenta,
            shape = RoundedCornerShape(20)
        )
        .padding(8.dp)


    ) {
        Text(text = tagName,modifier = Modifier.align(Alignment.Center))
    }

}
@Composable
@Preview
fun PreviewTag(){
    TagItems("Android")
}