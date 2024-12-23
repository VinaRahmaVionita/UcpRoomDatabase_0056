package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositoryBrg
import com.example.ucp2_roomdatabase.data.entity.Barang
import kotlinx.coroutines.launch



//menyimpan input form ke dalam entity
fun BarangEvent.toBarangEntity(): Barang = Barang (
    idBrg = idBrg,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaSuplier = namaSuplier
)

//data class variable yang menyimpan data input form
data class BarangEvent(
    val idBrg: String = "",
    val nama: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: String = "",
    val namaSuplier: String = ""
)