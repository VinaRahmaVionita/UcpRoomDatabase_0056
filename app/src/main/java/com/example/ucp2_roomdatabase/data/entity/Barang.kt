package com.example.ucp2_roomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Barang")
data class Barang(
    @PrimaryKey
    val idBrg: String,
    val nama: String,
    val deskripsi: String,
    val harga: String,
    val stok: String,
    val namaSuplier: String
)
