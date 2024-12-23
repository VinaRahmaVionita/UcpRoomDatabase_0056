package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositoryBrg
import com.example.ucp2_roomdatabase.data.entity.Barang
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

//untuk mengelola pengambilan semua data barang dari repository, menangani status loading, dan menangani kesalahan yang mungkin terjadi.
class HomeBarangViewModel(
    private val repositoryBrg: RepositoryBrg,
): ViewModel() {
    val homeUiState: StateFlow<HomeUiStateBrg> = repositoryBrg.getAllBrg()
        .filterNotNull()
        .map {
            HomeUiStateBrg(
                listBarang = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUiStateBrg(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiStateBrg(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiStateBrg(
                isLoading = true
            )
        )
}

//untuk menyimpan status UI terkait daftar barang
data class HomeUiStateBrg (
    val listBarang: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
)