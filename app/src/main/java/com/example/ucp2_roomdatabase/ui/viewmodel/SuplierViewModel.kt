package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositorySpl
import com.example.ucp2_roomdatabase.data.entity.Suplier
import kotlinx.coroutines.launch



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