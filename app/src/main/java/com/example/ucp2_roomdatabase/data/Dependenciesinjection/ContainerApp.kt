package com.example.ucp2_roomdatabase.data.Dependenciesinjection

import android.content.Context
import com.example.ucp2_roomdatabase.data.Repository.LocalRepositoryBrg
import com.example.ucp2_roomdatabase.data.Repository.LocalRepositorySpl
import com.example.ucp2_roomdatabase.data.Repository.RepositoryBrg
import com.example.ucp2_roomdatabase.data.Repository.RepositorySpl
import com.example.ucp2_roomdatabase.data.database.TokoDatabase


//Menyediakan Akses ke Repository
interface InterfaceContainerApp {
    val repositoryBrg: RepositoryBrg
    val repositorySpl: RepositorySpl
}


