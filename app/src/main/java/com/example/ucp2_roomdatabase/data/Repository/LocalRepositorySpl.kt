package com.example.ucp2_roomdatabase.data.Repository

import com.example.ucp2_roomdatabase.data.dao.SuplierDao
import com.example.ucp2_roomdatabase.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl (
    private val suplierDao: SuplierDao
) : RepositorySpl {
    override suspend fun insertSpl(suplier: Suplier) {
        suplierDao.insertSuplier(suplier)
    }
    override fun getAllSpl(): Flow<List<Suplier>> {
        return suplierDao.getAllSuplier()
    }

    override fun getAllSuplierNames(): Flow<List<String>> {
        return suplierDao.getAllSuplierNames()
    }

    override fun getSpl(idSpl: String): Flow<Suplier> {
        return suplierDao.getSuplier(idSpl)
    }

}