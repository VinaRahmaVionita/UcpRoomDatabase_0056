package com.example.ucp2_roomdatabase.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2_roomdatabase.ui.view.DestinasiInsertBrg
import com.example.ucp2_roomdatabase.ui.view.DestinasiInsertSpl
import com.example.ucp2_roomdatabase.ui.view.DetailBrgView
import com.example.ucp2_roomdatabase.ui.view.DetailSplView
import com.example.ucp2_roomdatabase.ui.view.HomeBarangView
import com.example.ucp2_roomdatabase.ui.view.HomeMenuView
import com.example.ucp2_roomdatabase.ui.view.HomeSuplierView
import com.example.ucp2_roomdatabase.ui.view.InsertBrgView
import com.example.ucp2_roomdatabase.ui.view.InsertSplView
import com.example.ucp2_roomdatabase.ui.view.UpdateBrgView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeMenu.route
    ) {
        composable(DestinasiHomeMenu.route) {
            HomeMenuView(
                onBarangClick = { navController.navigate(DestinasiHomeBarang.route) },
                onSuplierClick = { navController.navigate(DestinasiHomeSuplier.route) }
            )
        }

        //tampilan home suplier
        composable(
            route = DestinasiHomeSuplier.route
        ) {
            HomeSuplierView(
                onBack = {
                    navController.popBackStack()
                },
                onAddSpl = {
                    navController.navigate(DestinasiInsertSpl.route)
                },
                modifier = modifier
            )
        }

        //tampilan home barang
        composable(
            route = DestinasiHomeBarang.route) {
            HomeBarangView(
                onDetailClick = { idBrg ->
                    navController.navigate("${DestinasiDetailBrg.route}/$idBrg")
                },
                onAddBrg = {
                    navController.navigate(DestinasiInsertBrg.route)
                },
                modifier = modifier
            )
        }

        //tampilan insert Suplier
        composable(
            route = DestinasiInsertSpl.route
        ){
            InsertSplView(
                onBack = { navController.popBackStack() },
                onNavigate = {
 //                   navController.popBackStack()
                },
                modifier = modifier
            )
        }

        //tampilan detail suplier
        composable(
            DestinasiDetailSpl.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailSpl.idSpl){
                    type = NavType.StringType
                }
            )
        ){
            val idSpl = it.arguments?.getString(DestinasiDetailSpl.idSpl)
            idSpl?.let { idSpl ->
                DetailSplView(
                    onBack = {
                        navController.popBackStack()
                    },
                )
            }
        }

        //tampilan detail barang
        composable(
            DestinasiDetailBrg.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.idBrg){
                    type = NavType.StringType
                }
            )
        ){
            val idBrg = it.arguments?.getString(DestinasiDetailBrg.idBrg)
            idBrg?.let { idBrg ->
                DetailBrgView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBrg.route}/$idBrg")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        //tampilan insert barang
        composable(
            route = DestinasiInsertBrg.route
        ){
            InsertBrgView(
                onBack = { navController.popBackStack() },
                onNavigate = {
//                    navController.popBackStack()
                },
                modifier = modifier
            )
        }

        //tampilan update brg
        composable(
            DestinasiUpdateBrg.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.idBrg) {
                    type = NavType.StringType
                }
            )
        )  {
            val idBrg = it.arguments?.getString(DestinasiDetailBrg.idBrg)
            idBrg?.let {
                DetailBrgView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBrg.route}/$idBrg")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            DestinasiUpdateBrg.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.idBrg) {
                    type = NavType.StringType
                }
            )
        ){
            UpdateBrgView(
                onBack = { navController.popBackStack() },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}
