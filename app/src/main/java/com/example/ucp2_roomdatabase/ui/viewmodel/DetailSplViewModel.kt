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


//untuk mengelola status dan logika aplikasi yang berkaitan dengan tampilan detail data suplier
class DetailSplViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositorySpl: RepositorySpl,
) : ViewModel() {
    private val _idSpl: String = checkNotNull(savedStateHandle[DestinasiDetailSpl.idSpl])
    val detailUiStateSpl: StateFlow<DetailUiStateSpl> = repositorySpl.getSpl(_idSpl)
        .filterNotNull()
        .map {
            DetailUiStateSpl(
                detailUiEventSpl = it.toDetailUiEventSpl(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiStateSpl(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiStateSpl(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiStateSpl(
                isLoading = true,
            )
        )
}

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