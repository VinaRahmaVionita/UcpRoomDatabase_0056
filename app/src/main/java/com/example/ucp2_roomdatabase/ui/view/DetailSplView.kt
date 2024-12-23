package com.example.ucp2_roomdatabase.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_roomdatabase.data.entity.Suplier
import com.example.ucp2_roomdatabase.ui.customwidget.TopAppBar
import com.example.ucp2_roomdatabase.ui.viewmodel.DetailSplViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.DetailUiStateSpl
import com.example.ucp2_roomdatabase.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.toSuplierEntity




@Composable
fun ItemDetailSpl(
    modifier: Modifier = Modifier,
    suplier: Suplier
){
    Card (
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){

            ComponentDetailSpl(
                judul = "ID Suplier",
                isinya = suplier.idSpl
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailSpl(
                judul = "Nama",
                isinya = suplier.nama
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailSpl(
                judul = "Alamat",
                isinya = suplier.alamat
            )
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailSpl(
                judul = "Kontak",
                isinya = suplier.kontak
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

//menampilkan pasangan label (judul) dan nilai (isinya)
@Composable
fun ComponentDetailSpl(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}