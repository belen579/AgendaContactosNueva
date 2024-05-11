package com.antonio.agendajetpackcompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.antonio.agendajetpackcompose.ui.screens.Agenda
import com.antonio.agendajetpackcompose.ui.screens.AgendaAnhadirContacto
import com.antonio.agendajetpackcompose.ui.screens.AgendaDetalle
import com.antonio.agendajetpackcompose.ui.screens.EditarContacto

import com.antonio.agendajetpackcompose.ui.screens.Menu
import com.antonio.agendajetpackcompose.ui.viewmodel.AgendaViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController() //linea para recordar NavController entre pantallas
    val viewModel= remember{ AgendaViewModel() } //linea para recordar viewModel entre pantallas

    NavHost(navController, startDestination = Screens.Menu.route) {
        //pantalla principal con la navegación
        composable(route = Screens.Menu.route) {
            Menu(navController, viewModel) }//Nombre del fichero .kt al que navegar

        composable(route = Screens.Agenda.route) {
            Agenda(navController,viewModel) //Nombre de la función composable a la que navegar
        }

        composable(route = Screens.AgendaDetalle.route) {
            AgendaDetalle(navController,viewModel) //Nombre de la función composable a la que navegar
        }

        composable(route = Screens.AgendaAnhadirContacto.route) {
            AgendaAnhadirContacto(navController,viewModel) //Nombre de la función composable a la que navegar
        }

        composable(route = Screens.EditarContacto.route) {
            EditarContacto(navController,viewModel) //Nombre de la función composable a la que navegar
        }




    }
}