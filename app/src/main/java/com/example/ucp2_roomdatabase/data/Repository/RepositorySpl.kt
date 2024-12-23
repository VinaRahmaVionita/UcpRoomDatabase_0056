package com.example.ucp2_roomdatabase.data.Repository

import com.example.ucp2_roomdatabase.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {

    //insert
    suspend fun insertSpl(
        suplier: Suplier
    )
    fun getAllSuplierNames(): Flow<List<String>>
    //read
    fun getAllSpl(): Flow<List<Suplier>>
    fun getSpl(idSpl: String): Flow<Suplier>
}