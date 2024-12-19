package com.example.ucp_2.data.repository

import com.example.ucp_2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBrg {
    suspend fun insertBrg(barang: Barang)
    suspend fun updateBrg(barang: Barang)
    fun getBrg(id: String): Flow<Barang>
    fun getAllBrg(): Flow<List<Barang>>
    suspend fun deleteBrg(barang: Barang)
}