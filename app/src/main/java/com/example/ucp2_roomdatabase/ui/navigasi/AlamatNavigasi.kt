package com.example.ucp2_roomdatabase.ui.navigasi

interface AlamatNavigasi {
    val route: String
}

object DestinasiHomeMenu : AlamatNavigasi {
    override val route = "home_menu"
}

object DestinasiHomeBarang : AlamatNavigasi {
    override val route = "home_brg"
}

object DestinasiHomeSuplier : AlamatNavigasi {
    override val route = "home_spl"
}

object DestinasiDetailBrg : AlamatNavigasi {
    override val route = "detail_brg"
    const val idBrg = "idBrg"
    val routeWithArgs = "$route/{$idBrg}"
}

object DestinasiUpdateBrg : AlamatNavigasi {
    override val route = "update_brg"
    const val idBrg = "idBrg"
    val routeWithArgs = "$route/{$idBrg}"
}

object DestinasiDetailSpl : AlamatNavigasi {
    override val route = "detail_brg"
    const val idSpl = "idSpl"
    val routeWithArgs = "$route/{$idSpl}"
}


