package com.example.ucp2_roomdatabase.data.Repository

import com.example.ucp2_roomdatabase.data.dao.BarangDao
import com.example.ucp2_roomdatabase.data.entity.Barang
import kotlinx.coroutines.flow.Flow


//sebagai perantara antara aplikasi dan database Room untuk entitas Barang
class LocalRepositoryBrg (
    private val barangDao: BarangDao
) : RepositoryBrg {
    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllBrg(): Flow<List<Barang>> {
        return barangDao.getAllBarang()
    }

    override fun getBrg(idBrg: String): Flow<Barang> {
        return barangDao.getBarang(idBrg)
    }

    override suspend fun updateBrg(barang: Barang) {
        barangDao.updateBarang(barang)
    }

    override suspend fun deleteBrg(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

}

