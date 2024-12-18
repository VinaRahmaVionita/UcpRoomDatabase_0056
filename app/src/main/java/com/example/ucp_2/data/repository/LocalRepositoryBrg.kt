package com.example.ucp_2.data.repository

import com.example.ucp_2.data.dao.BarangDao
import com.example.ucp_2.data.entity.Barang

class LocalRepositoryBrg(
    private val barangDao: BarangDao
) : RepositoryBrg {
    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

}
