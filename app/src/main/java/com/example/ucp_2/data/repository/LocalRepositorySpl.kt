package com.example.ucp_2.data.repository

import com.example.ucp_2.data.dao.SuplierDao
import com.example.ucp_2.data.entity.Suplier
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

    override fun getSpl(id: String): Flow<Suplier> {
        return suplierDao.getSuplier(id)
    }

}