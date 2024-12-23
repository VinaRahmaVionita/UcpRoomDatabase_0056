package com.example.ucp2_roomdatabase.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_roomdatabase.data.entity.Barang
import com.example.ucp2_roomdatabase.ui.customwidget.TopAppBar
import com.example.ucp2_roomdatabase.ui.viewmodel.DetailBrgViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.DetailUiStateBrg
import com.example.ucp2_roomdatabase.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_roomdatabase.ui.viewmodel.toBarangEntity



@Composable
fun BodyDetailBrg(
    modifier: Modifier = Modifier,
    detailUiStateBrg: DetailUiStateBrg = DetailUiStateBrg(),
    onDeleteClick: () -> Unit = { }
){
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiStateBrg.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Loading...")
            }
        }

        detailUiStateBrg.isUiEventNotEmpty -> {
            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailBrg(
                    barang = detailUiStateBrg.detailUiEventBrg.toBarangEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete")
                }
                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = {
                            deleteConfirmationRequired = false
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailUiStateBrg.isUiEventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Data barang tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

//Menampilkan informasi barang dalam bentuk Card
@Composable
fun ItemDetailBrg(
    modifier: Modifier = Modifier,
    barang: Barang
) {
    //  warna kartu berdasarkan stok
    val cardColor = when {
        barang.stok == "0" -> Color.Gray
        barang.stok.toIntOrNull() in 1..10 -> Color.Red
        barang.stok.toIntOrNull() != null && barang.stok.toInt() > 10 -> Color.Green
        else -> MaterialTheme.colorScheme.primaryContainer
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = cardColors(
            containerColor = cardColor,
            contentColor = Color.Black
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailBrg(
                judul = "IdBrg",
                isinya = barang.idBrg,
                textColor = Color.Black
            )

            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(
                judul = "Nama",
                isinya = barang.nama,
                textColor = Color.Black
            )

            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(
                judul = "Deskripsi",
                isinya = barang.deskripsi,
                textColor = Color.Black
            )

            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(
                judul = "Harga",
                isinya = barang.harga,
                textColor = Color.Black
            )

            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(
                judul = "Stok",
                isinya = barang.stok,
                textColor = Color.Black
            )

            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(
                judul = "Nama Suplier",
                isinya = barang.namaSuplier,
                textColor = Color.Black
            )

            Spacer(modifier = Modifier.padding(4.dp))
        }
    }
}

//menampilkan label dan isi informasi barang
@Composable
fun ComponentDetailBrg(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
    textColor: Color = Color.Black
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}


//Dialog konfirmasi saat pengguna ingin menghapus barang
@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text("Delete Barang") },
        text = { Text(" Apakah anda yakin ingin menghapus barang ini? ") },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = onDeleteCancel
            ) {
                Text(text = "Cancel") //Membatalkan penghapusan.
            }
        },
        confirmButton = {
            TextButton(
                onClick = onDeleteConfirm
            ) {
                Text(text = "Yes") //Mengonfirmasi penghapusan
            }
        }
    )
}