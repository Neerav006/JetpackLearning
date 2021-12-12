package com.example.myapplication.common


import ConnectionState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import currentConnectivityState

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import observeConnectivityAsFlow


@Composable
fun currentConnectionState(): ConnectionState {
    val context = LocalContext.current
    return remember { context.currentConnectivityState }
}


@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current
    return produceState(initialValue = context.currentConnectivityState) {
        context.observeConnectivityAsFlow().distinctUntilChanged().collect { value = it }
    }
}