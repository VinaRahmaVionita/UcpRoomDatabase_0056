package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositorySpl
import com.example.ucp2_roomdatabase.data.entity.Suplier
import kotlinx.coroutines.launch

//untuk mengelola logika penyimpanan data supplier 
class SuplierViewModel (private val repositorySpl: RepositorySpl) : ViewModel() {
    var uiState by mutableStateOf(SplUIState())

    fun updateState(suplierEvent: SuplierEvent){
        uiState = uiState.copy(
            suplierEvent = suplierEvent
        )
    }

    //validasi data input pengguna
    private fun validateFields() : Boolean {
        val event = uiState.suplierEvent
        val errorStateSpl = FormErrorStateSpl(
            idSpl = if (event.idSpl.isNotEmpty()) null else "Id Suplier tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak Suplier tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat Suplier tidak boleh kosong"
        )

        uiState = uiState.copy(
            isEntryValid = errorStateSpl
        )
        return errorStateSpl.isValid()
    }

    //Menyimpan data ke repository
    fun saveSuplier() {
        val currentEvent = uiState.suplierEvent

        if (validateFields()) {
                viewModelScope.launch {
                    try {
                        repositorySpl.insertSpl(currentEvent.toSuplierEntity())
                        uiState = uiState.copy(
                            snackBarMessage = "Data berhasil disimpan",
                            suplierEvent = SuplierEvent(),
                            isEntryValid = FormErrorStateSpl()
                        )
                    }catch (e: Exception) {
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

//data class yang menyimpan berbagai informasi terkait UI
data class SplUIState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorStateSpl = FormErrorStateSpl(),
    val listSuplier: List<Suplier> = listOf(),
    val snackBarMessage: String? = null,
    val isLoading: Boolean = false,
)

//untuk menghandle atau memberikan nilai validasi apakah benar atau salah
data class FormErrorStateSpl(
    val idSpl: String? = null,
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
){
    fun isValid(): Boolean {
        return idSpl == null && nama == null && kontak == null && alamat == null
    }
}

//menyimpan input form ke dalam entity
fun SuplierEvent.toSuplierEntity(): Suplier = Suplier (
    idSpl = idSpl,
    nama = nama,
    kontak = kontak,
    alamat = alamat
)


//data class variable yang menyimpan data input form
data class SuplierEvent(
    val idSpl: String = "",
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = "",
)