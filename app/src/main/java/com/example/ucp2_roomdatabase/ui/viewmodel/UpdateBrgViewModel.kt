package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositoryBrg
import com.example.ucp2_roomdatabase.data.entity.Barang
import com.example.ucp2_roomdatabase.ui.navigasi.DestinasiUpdateBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

//untuk memperbarui data barang yang ada di dalam database
class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel() {
    var updateUIState by mutableStateOf(BrgUIState())
        private set
    private val _idBrg: String = checkNotNull(savedStateHandle[DestinasiUpdateBrg.idBrg])

    init {
        viewModelScope.launch {
            // Ambil data barang berdasarkan ID
            updateUIState = repositoryBrg.getBrg(_idBrg)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun updateStateBrg(barangEvent: BarangEvent) {
        updateUIState = updateUIState.copy(
            barangEvent = barangEvent
        )
    }

    //memvalidasi kalau tidak ada yang di inputkan
    fun validasiFields(): Boolean {
        val event = updateUIState.barangEvent
        val errorStateBrg = FormErrorStateBrg(
            idBrg = if (event.idBrg.isNotEmpty()) null else "Id Barang tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi Barang tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga Barang tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok Barang tidak boleh kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier Barang tidak boleh kosong"
        )
        updateUIState = updateUIState.copy(
            isEntryValid = errorStateBrg
        )
        return errorStateBrg.isValid()
    }

    suspend fun updateDatabrg() {
        val currentEvent = updateUIState.barangEvent
        if (validasiFields()) {
            try {
                repositoryBrg.updateBrg(currentEvent.toBarangEntity())
                updateUIState = updateUIState.copy(
                    snackBarMessage = "Data berhasil diperbaharui",
                    barangEvent = BarangEvent(),
                    isEntryValid = FormErrorStateBrg()
                )
            } catch (e: Exception) {
                updateUIState = updateUIState.copy(
                    snackBarMessage = "Data gagal diperbaharui"
                )
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diperbaharui"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(
            snackBarMessage = null
        )
    }
}

fun Barang.toUIStateBrg(): BrgUIState = BrgUIState(
    barangEvent =  this.toDetailUiEvent()
)