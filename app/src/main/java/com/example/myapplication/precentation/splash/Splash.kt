package com.example.myapplication.precentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController) {

    val isAnimationStart = remember {
        mutableStateOf(false)
    }
    val animateFloat = animateFloatAsState(
        targetValue =
        if (isAnimationStart.value) 1f else 0f,

        animationSpec =
        tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        isAnimationStart.value = true
        delay(2000)
        navController.popBackStack()
        navController.navigate("list_screen")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Default.Email, contentDescription = "",
            modifier = Modifier.size(100.dp).alpha(animateFloat.value),
            tint = Color.White
            )


    }


}