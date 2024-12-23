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

//Menyediakan Akses ke DAO Database
class ContainerApp (private val context: Context) : InterfaceContainerApp {
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(TokoDatabase.getDatabase(context).barangDao())
    }

    override val repositorySpl: RepositorySpl by lazy {
        LocalRepositorySpl(TokoDatabase.getDatabase(context).suplierDao())
    }
}
