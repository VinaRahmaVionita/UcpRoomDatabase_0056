package com.example.ucp2_roomdatabase.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_roomdatabase.ui.customwidget.TopAppBar
import com.example.ucp2_roomdatabase.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.UpdateBrgViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateBrgViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.updateUIState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        println("LaunchedEffect Triggered")
        uiState.snackBarMessage?.let { message ->
            println("Snackbar Message received: $message")
            //Validasi dan Pembaruan Data
            coroutineScope.launch {
                println("Launching coroutine for snackbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                    //Menampilkan Snackbar
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                judul = "Update Barang",
                showBackButton = true,
                onBack = onBack
            )
        }
    ){ padding ->
        Column (
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ){
            InsertBodyBrg(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateStateBrg(updatedEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validasiFields()) {
                            viewModel.updateDatabrg()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
                }
            )
        }
    }
}