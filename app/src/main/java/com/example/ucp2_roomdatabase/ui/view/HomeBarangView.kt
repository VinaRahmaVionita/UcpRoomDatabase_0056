package com.example.ucp2_roomdatabase.ui.view

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_roomdatabase.data.entity.Barang
import com.example.ucp2_roomdatabase.ui.customwidget.TopAppBar
import com.example.ucp2_roomdatabase.ui.viewmodel.HomeBarangViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.HomeUiStateBrg
import com.example.ucp2_roomdatabase.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeBarangView(
    modifier: Modifier = Modifier,
    viewModel: HomeBarangViewModel = viewModel ( factory = PenyediaViewModel.Factory ),
    onAddBrg: () -> Unit = { },
    onDetailClick: (String) -> Unit = { },
){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        topBar = {
            TopAppBar(
                judul = "Home Barang",
                modifier = modifier,
                showBackButton = false,
                onBack = { }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddBrg,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ){
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Tambah Barang"
                )
            }
        }
    ) {innerPadding ->
        val homeUiStateBrg by viewModel.homeUiState.collectAsState()
        HomeBodyBarangView(
            homeUiStateBrg = homeUiStateBrg,
            onClick = { onDetailClick(it) },
            modifier = modifier.padding(innerPadding)
        )
    }
}

//Menangani tampilan berdasarkan kondisi state
@Composable
fun HomeBodyBarangView(
    homeUiStateBrg: HomeUiStateBrg,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        homeUiStateBrg.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        homeUiStateBrg.isError -> {
            LaunchedEffect(homeUiStateBrg.errorMessage) {
                homeUiStateBrg.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }

        homeUiStateBrg.listBarang.isEmpty() -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak Ada Data Barang",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        else -> {
            ListBarang(
                listBarang = homeUiStateBrg.listBarang,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

//menampilkan daftar barang menggunakan komponen CardBarang
@Composable
fun ListBarang(
    listBarang: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ){
        items(
            items = listBarang,
            itemContent = { brg ->
                CardBarang(
                    barang = brg,
                    onClick = { onClick(brg.idBrg) }
                )
            }
        )
    }
}

//Menampilkan informasi barang dalam bentuk kartu
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardBarang(
    barang: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    val cardColor = when {
        barang.stok.toInt() == 0 -> Color.Gray
        barang.stok.toInt() in 1..10 -> Color.Red
        else -> Color.Green
    }

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = cardColors(
            containerColor = cardColor // Set background color based on stock
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange, contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = barang.idBrg,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart, contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = barang.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info, contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = barang.deskripsi,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Email, contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = barang.harga,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle, contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = barang.stok,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person, contentDescription = " "
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = barang.namaSuplier,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }
}
