package com.example.ucp2_roomdatabase.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.ucp2_roomdatabase.ui.viewmodel.BarangEvent
import com.example.ucp2_roomdatabase.ui.viewmodel.BarangViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.BrgUIState
import com.example.ucp2_roomdatabase.ui.viewmodel.FormErrorStateBrg
import com.example.ucp2_roomdatabase.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsertBrg : AlamatNavigasi {
    override val route: String = "insert_brg"
}

@Composable
fun InsertBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BarangViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage){
        uiState.snackBarMessage?.let {
            message -> coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Barang",
                modifier = modifier
            )
            InsertBodyBrg(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateStateBrg(updatedEvent)
                },
                onClick = {
                    viewModel.saveBarang()
                    onNavigate()
                }
            )
        }

    }
}

@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUIState,
    onClick: () -> Unit
) {
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorStateBrg = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorStateBrg: FormErrorStateBrg = FormErrorStateBrg(),
    modifier: Modifier = Modifier
) {
    val listSuplier = remember { mutableStateListOf("Vina", "Rahma", "Vionita") }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.idBrg,
            onValueChange = {
                onValueChange(barangEvent.copy(idBrg = it))
            },
            label = {
                Text(text = "ID Barang")
            },
            isError = errorStateBrg.idBrg != null,
            placeholder = { Text("Masukkan ID Barang") },
        )
        Text(
            text = errorStateBrg.idBrg ?: " ",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = {
                Text(text = "Nama Barang")
            },
            isError = errorStateBrg.nama != null,
            placeholder = { Text("Masukkan Nama Barang") },
        )
        Text(
            text = errorStateBrg.nama ?: " ",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = {
                Text(text = "Deskripsi Barang")
            },
            isError = errorStateBrg.deskripsi != null,
            placeholder = { Text("Masukkan Deskripsi Barang") },
        )
        Text(
            text = errorStateBrg.deskripsi ?: " ",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = {
                Text(text = "Harga Barang")
            },
            isError = errorStateBrg.harga != null,
            placeholder = { Text("Masukkan Harga Barang") },
        )
        Text(
            text = errorStateBrg.harga ?: " ",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = {
                onValueChange(barangEvent.copy(stok = it))
            },
            label = {
                Text(text = "Stok Barang")
            },
            isError = errorStateBrg.stok != null,
            placeholder = { Text("Masukkan Stok Barang") },
        )
        Text(
            text = errorStateBrg.stok ?: " ",
            color = Color.Red
        )

        DropdownNama(
            label = "Nama Supplier",
            options = listSuplier,
            selectedOption = barangEvent.namaSuplier,
            onOptionSelected = {
                    selectedSuplier -> onValueChange(barangEvent.copy(namaSuplier = selectedSuplier))
            },
            isError = errorStateBrg.namaSuplier != null,
            errorMessage = errorStateBrg.namaSuplier
        )
    }
}

@Composable
fun DropdownNama(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
) {
    var expanded by remember { mutableStateOf(false) }
    var currentSelection by remember { mutableStateOf(selectedOption) }

    Column {
        OutlinedTextField(
            value = currentSelection,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown,
                        contentDescription = " "
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            isError = isError
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        currentSelection = option
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }

        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
