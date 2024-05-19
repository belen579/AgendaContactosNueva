package com.example.agendacontactos_objetos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.agendacontactos_objetos.Data.viewModelContacto
import com.example.agendacontactos_objetos.Screens.fichacontacto
import com.example.agendacontactos_objetos.Screens.fichacontactoModificar


import com.example.agendacontactosnueva.preview

@Composable
fun Navigation(){

    val navController = rememberNavController()
    val context = LocalContext.current
    val viewmodelcontacto =  (viewModelContacto(context))
    val id:Int =0

    NavHost(navController, startDestination = Screens.inicio.route){
        composable(route=Screens.inicio.route){
            preview(navController) }
        composable(route= Screens.ficha.route){
            fichacontacto(viewmodelcontacto,navController)  //Nombre de la funcion a ejecutar
        }

        composable(route= Screens.fichamodificar.route){
                backStackEntry ->
            val idUsuario = backStackEntry.arguments?.getString("idUsuario")

            fichacontactoModificar(viewmodelcontacto,navController, idUsuario)  //Nombre de la funcion a ejecutar
        }


    }


}




