package com.example.ucp2_roomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2_roomdatabase.data.entity.Suplier
import kotlinx.coroutines.flow.Flow


//menambahkan operasi CR untuk tabel suplier
@Dao
interface SuplierDao {
    @Query("SELECT nama FROM suplier")
    fun getAllSuplierNames(): Flow<List<String>>

    @Query("SELECT * FROM Suplier ORDER BY nama ASC")
    fun getAllSuplier(): Flow<List<Suplier>>

    @Query("SELECT * FROM Suplier WHERE idSpl = :idSpl")
    fun getSuplier(idSpl: String): Flow<Suplier>

    @Insert
    suspend fun insertSuplier(
        suplier: Suplier
    )
}