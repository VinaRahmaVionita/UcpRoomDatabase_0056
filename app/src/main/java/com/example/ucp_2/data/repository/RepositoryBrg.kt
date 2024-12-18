package com.example.ucp_2.data.repository

import com.example.ucp_2.data.entity.Barang

interface RepositoryBrg {
    suspend fun insertBrg(barang: Barang)
}