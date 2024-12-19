package com.example.ucp_2.data.repository

import com.example.ucp_2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {

    //insert
    suspend fun insertSpl(
        suplier: Suplier
    )
    //read
    fun getAllSpl(): Flow<List<Suplier>>
    fun getSpl(id: String): Flow<Suplier>
}