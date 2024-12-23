package com.example.ucp2_roomdatabase.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_roomdatabase.ui.customwidget.TopAppBar
import com.example.ucp2_roomdatabase.ui.navigasi.AlamatNavigasi
import com.example.ucp2_roomdatabase.ui.viewmodel.FormErrorStateSpl
import com.example.ucp2_roomdatabase.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.SplUIState
import com.example.ucp2_roomdatabase.ui.viewmodel.SuplierEvent
import com.example.ucp2_roomdatabase.ui.viewmodel.SuplierViewModel
import kotlinx.coroutines.launch



@Composable
fun InsertBodySuplier(
    modifier: Modifier = Modifier,
    onValueChange: (SuplierEvent) -> Unit = { },
    uiState: SplUIState,
    onClick: () -> Unit
){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSuplier(
            suplierEvent = uiState.suplierEvent,
            onValueChange = onValueChange,
            errorStateSuplier = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red) //warna button
        ) {
            Text("Simpan", color = Color.White)
        }
    }
}

@Preview (showBackground = true)
@Composable
fun FormSuplier(
    suplierEvent: SuplierEvent = SuplierEvent(),
    onValueChange: (SuplierEvent) -> Unit = { },
    errorStateSuplier: FormErrorStateSpl = FormErrorStateSpl(),
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.idSpl,
            onValueChange = {
                onValueChange(suplierEvent.copy(idSpl = it))
            },
            label = { Text("ID Suplier") },
            isError = errorStateSuplier.idSpl != null,
            placeholder = { Text("Masukkan ID SUplier") },
        )
        Text(
            text = errorStateSuplier.nama ?: " ",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.nama,
            onValueChange = {
                onValueChange(suplierEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorStateSuplier.nama != null,
            placeholder = { Text("Masukkan Nama") },
        )
        Text(
            text = errorStateSuplier.nama ?: " ",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamat,
            onValueChange = {
                onValueChange(suplierEvent.copy(alamat = it))
            },
            label = { Text("Alamat") },
            isError = errorStateSuplier.alamat != null,
            placeholder = { Text("Masukkan Alamat") },
        )
        Text(
            text = errorStateSuplier.alamat ?: " ",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontak,
            onValueChange = {
                onValueChange(suplierEvent.copy(kontak = it))
            },
            label = { Text("Kontak") },
            isError = errorStateSuplier.kontak != null,
            placeholder = { Text("Masukkan Kontak") },
        )
        Text(
            text = errorStateSuplier.kontak ?: " ",
            color = Color.Red
        )

    }
}