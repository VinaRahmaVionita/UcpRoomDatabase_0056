package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositorySpl
import com.example.ucp2_roomdatabase.data.entity.Suplier
import kotlinx.coroutines.launch



//data class variable yang menyimpan data input form
data class SuplierEvent(
    val idSpl: String = "",
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = "",
)