package com.example.ucp2_roomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Suplier")
data class Suplier(
    @PrimaryKey
    val idSpl: String,
    val nama: String,
    val alamat: String,
    val kontak: String
)
