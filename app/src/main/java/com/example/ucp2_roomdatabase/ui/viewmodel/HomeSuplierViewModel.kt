package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_roomdatabase.data.Repository.RepositorySpl
import com.example.ucp2_roomdatabase.data.entity.Suplier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn


//untuk mengelola pengambilan semua data suplier dari repository, menangani status loading, dan menangani kesalahan yang mungkin terjadi.
class HomeSuplierViewModel(
    private val repositorySpl: RepositorySpl,
): ViewModel() {
    val homeUiState: StateFlow<HomeUiStateSpl> = repositorySpl.getAllSpl()
        .filterNotNull()
        .map {
            HomeUiStateSpl(
                listSuplier = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(HomeUiStateSpl(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiStateSpl(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiStateSpl(
                isLoading = true
            )
        )
}

//untuk menyimpan status UI terkait daftar suplier
data class HomeUiStateSpl (
    val listSuplier: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
)