package com.example.agendacontactos_objetos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agendacontactos_objetos.Data.Contacto
import com.example.agendacontactos_objetos.Data.viewModelContacto
import com.example.agendacontactos_objetos.Screens.fichacontacto
import com.example.agendacontactos_objetos.Screens.fichacontactoModificar

import com.example.agendacontactos_objetos.preview

@Composable
fun Navigation(){

    val navController = rememberNavController()
    val context = LocalContext.current
    val viewmodelcontacto =  (viewModelContacto(context))

    NavHost(navController, startDestination = Screens.inicio.route){
        composable(route=Screens.inicio.route){
            preview(navController) }
        composable(route= Screens.ficha.route){
            fichacontacto(viewmodelcontacto,navController)  //Nombre de la funcion a ejecutar
        }

        composable(route= Screens.ficha.route){
            fichacontactoModificar(viewmodelcontacto,navController)  //Nombre de la funcion a ejecutar
        }


    }


}