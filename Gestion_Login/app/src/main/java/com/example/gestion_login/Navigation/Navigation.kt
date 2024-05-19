package com.example.gestion_login.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestion_login.screens.Login
import com.example.gestion_login.screens.principal
import com.example.gestion_login.screens.viewModelUsuario

@Composable
fun Navigation(){

    val navController = rememberNavController()
    val viewmodel = viewModelUsuario()

    NavHost(navController, startDestination = Screens.inicio.route){
        composable(route=Screens.inicio.route){ Login(viewmodel) }
        composable(route=Screens.inicio.route){  principal() }

        }

        }






