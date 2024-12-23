package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositoryBrg
import com.example.ucp2_roomdatabase.data.entity.Barang
import com.example.ucp2_roomdatabase.ui.navigasi.DestinasiDetailBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch




//data class yang menggambarkan status UI saat menampilkan detail barang
data class DetailUiStateBrg(
    val detailUiEventBrg: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
){
    val isUiEventEmpty: Boolean
        get() = detailUiEventBrg == BarangEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEventBrg != BarangEvent()
}

//mengonversi data Barang ke dalam format yang cocok untuk ditampilkan di UI
fun Barang.toDetailUiEvent() : BarangEvent {
    return BarangEvent(
        idBrg = idBrg,
        nama = nama,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        namaSuplier = namaSuplier
    )
}