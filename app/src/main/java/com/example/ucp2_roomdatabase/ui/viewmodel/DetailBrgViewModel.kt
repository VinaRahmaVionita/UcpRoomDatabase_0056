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


//untuk mengelola status dan logika aplikasi yang berkaitan dengan tampilan detail barang
class DetailBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {
    private val _idBrg: String = checkNotNull(savedStateHandle[DestinasiDetailBrg.idBrg])
    val detailUiStateBrg: StateFlow<DetailUiStateBrg> = repositoryBrg.getBrg(_idBrg)
        .filterNotNull()
        .map {
            DetailUiStateBrg(
                detailUiEventBrg = it.toDetailUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailUiStateBrg(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailUiStateBrg(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailUiStateBrg(
                isLoading = true
            )
        )
    //fungsi untuk menghapus barang
    fun deleteBrg(){
        detailUiStateBrg.value.detailUiEventBrg.toBarangEntity().let {
            viewModelScope.launch {
                repositoryBrg.deleteBrg(it)
            }
        }
    }
}

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