package com.example.ucp2_roomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2_roomdatabase.data.entity.Barang
import kotlinx.coroutines.flow.Flow

//menambahkan operasi CRUD untuk entitas barang
//@Dao adalah anotasi berfungsi sebagai interface untuk berinteraksi dengan tabel Barang dalam database Room
@Dao
interface BarangDao {
    @Query("SELECT * FROM Barang ORDER BY nama ASC")
    fun getAllBarang(): Flow<List<Barang>>

    @Query("SELECT * FROM Barang WHERE idBrg = :idBrg")
    fun getBarang(idBrg: String): Flow<Barang>

    @Insert
    suspend fun insertBarang(
        barang: Barang
    )

    @Delete
    suspend fun deleteBarang(
        barang: Barang
    )

    @Update
    suspend fun updateBarang(
        barang: Barang
    )
}