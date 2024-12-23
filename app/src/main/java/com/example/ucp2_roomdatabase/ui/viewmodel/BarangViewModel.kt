package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositoryBrg
import com.example.ucp2_roomdatabase.data.entity.Barang
import kotlinx.coroutines.launch

class BarangViewModel (
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {
    var uiState by mutableStateOf(BrgUIState())

    //untuk memperbarui state dari barang yang diinputkan
    fun updateStateBrg(barangEvent: BarangEvent) {
        uiState = uiState.copy(
            barangEvent = barangEvent
        )
    }

    //validasi data input pengguna
    private fun validateFields() : Boolean {
        val event = uiState.barangEvent
        val errorStateBrg = FormErrorStateBrg(
            idBrg = if (event.idBrg.isNotEmpty()) null else "Id Barang tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi Barang tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga Barang tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok Barang tidak boleh kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier Barang tidak boleh kosong"
        )
        uiState = uiState.copy(
            isEntryValid = errorStateBrg
        )
        return errorStateBrg.isValid()
    }

    //Menyimpan data ke repository
    fun saveBarang() {
        val currentEvent = uiState.barangEvent
        if (validateFields()){
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorStateBrg()
                    )
                } catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda"
            )
        }
    }
    fun resetSnackBarMessage() {
        uiState = uiState.copy(
            snackBarMessage = null
        )
    }
}

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(), //Menyimpan data barang yang diinput oleh pengguna
    val isEntryValid: FormErrorStateBrg = FormErrorStateBrg(),//Menyimpan status validasi form (apakah data valid atau tidak).
    val snackBarMessage: String? = null, //Menyimpan pesan untuk ditampilkan di snackbar
)

//menyimpan pesan kesalahan
data class FormErrorStateBrg(
    val idBrg: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null
){
    //untuk mengecek apakah semua field valid atau tidak
    fun isValid(): Boolean {
        return idBrg == null && nama == null && deskripsi == null &&
                harga == null && stok == null && namaSuplier == null
    }
}

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