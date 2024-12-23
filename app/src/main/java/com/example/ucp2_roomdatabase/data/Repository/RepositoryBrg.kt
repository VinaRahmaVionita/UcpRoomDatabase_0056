package com.example.ucp2_roomdatabase.data.Repository

import com.example.ucp2_roomdatabase.data.entity.Barang
import kotlinx.coroutines.flow.Flow

//mendefinisikan mendefinisikan cara aplikasi akan berinteraksi dengan data Barang
interface RepositoryBrg {
    suspend fun insertBrg(
        barang: Barang
    )
    suspend fun updateBrg(
        barang: Barang
    )
    fun getBrg(idBrg: String): Flow<Barang>
    fun getAllBrg(): Flow<List<Barang>>
    suspend fun deleteBrg(
        barang: Barang
    )
}