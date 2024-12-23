package com.example.ucp2_roomdatabase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2_roomdatabase.data.dao.BarangDao
import com.example.ucp2_roomdatabase.data.dao.SuplierDao
import com.example.ucp2_roomdatabase.data.entity.Barang
import com.example.ucp2_roomdatabase.data.entity.Suplier

@Database(entities = [Barang::class, Suplier::class], version = 1, exportSchema = false)
abstract class TokoDatabase : RoomDatabase() {

    //Mendefinisikan fungsi untuk mengakses data barang dan suplier
    abstract fun barangDao(): BarangDao
    abstract fun suplierDao(): SuplierDao


}