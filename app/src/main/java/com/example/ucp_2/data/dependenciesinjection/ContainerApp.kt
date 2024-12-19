package com.example.ucp_2.data.dependenciesinjection

import android.content.Context
import com.example.ucp_2.data.database.TokoDatabase
import com.example.ucp_2.data.repository.LocalRepositoryBrg
import com.example.ucp_2.data.repository.LocalRepositorySpl
import com.example.ucp_2.data.repository.RepositoryBrg
import com.example.ucp_2.data.repository.RepositorySpl

interface InterfaceContainerApp {
   val repositoryBrg: RepositoryBrg
   val repositorySpl: RepositorySpl
}

class ContainerApp (private val context: Context) : InterfaceContainerApp {
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepositoryBrg(TokoDatabase.getDatabase(context).barangDao())
    }

    override val repositorySpl: RepositorySpl by lazy {
        LocalRepositorySpl(TokoDatabase.getDatabase(context).suplierDao())
    }
}
