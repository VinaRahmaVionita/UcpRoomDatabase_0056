package com.example.ucp2_roomdatabase.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2_roomdatabase.TokoApp


//memudahkan pengelolaan logika di dalam aplikasi
object PenyediaViewModel {
    //memastikan bahwa setiap ViewModel yang digunakan memiliki akses yang benar ke dependensinya
    val Factory = viewModelFactory {
        initializer {
            HomeBarangViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            BarangViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }

        initializer {
            HomeSuplierViewModel(
                TokoApp().containerApp.repositorySpl
            )
        }

        initializer {
            SuplierViewModel(
                TokoApp().containerApp.repositorySpl
            )
        }

        initializer {
            DetailSplViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositorySpl
            )
        }

    }
}

