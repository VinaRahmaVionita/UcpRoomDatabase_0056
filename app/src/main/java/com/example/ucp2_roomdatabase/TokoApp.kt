package com.example.ucp2_roomdatabase

import android.app.Application
import com.example.ucp2_roomdatabase.data.Dependenciesinjection.ContainerApp


//menginisialisasi aplikasi dan mengatur berbagai dependensi yang digunakan oleh aplikasi
class TokoApp : Application(){
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this)
    }
}