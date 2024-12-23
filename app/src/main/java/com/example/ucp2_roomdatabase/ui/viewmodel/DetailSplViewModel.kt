package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositorySpl
import com.example.ucp2_roomdatabase.data.entity.Suplier
import com.example.ucp2_roomdatabase.ui.navigasi.DestinasiDetailSpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


//data class yang menggambarkan status UI saat menampilkan detail suplier
data class DetailUiStateSpl(
    val detailUiEventSpl: SuplierEvent = SuplierEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
){
    val isUiEventEmpty: Boolean
        get() = detailUiEventSpl == SuplierEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEventSpl != SuplierEvent()
}

//mengambil data dari objek Suplier ke dalam format yang lebih sesuai untuk ditampilkan di UI
fun Suplier.toDetailUiEventSpl() : SuplierEvent {
    return SuplierEvent(
        idSpl = idSpl,
        nama = nama,
        kontak = kontak,
        alamat = alamat
    )
}