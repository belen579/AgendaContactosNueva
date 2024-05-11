package com.antonio.agendajetpackcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.antonio.agendajetpackcompose.R
import com.antonio.agendajetpackcompose.ui.navigation.Screens
import com.antonio.agendajetpackcompose.ui.viewmodel.AgendaViewModel

@Composable
fun Menu(navController: NavHostController, viewModel: AgendaViewModel) {
    var context= LocalContext.current
    var colorFondo = Color(25,20,52)
    Column(modifier= Modifier
        .fillMaxSize()
        .background(colorFondo), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//        Button(onClick = {
//
//            viewModel.guardarListaEnFichero(context)
//            navController.navigate(route=Screens.Agenda.route)
//
//        },
//            contentPadding = PaddingValues(0.dp), // Elimina el relleno interno del botón
//            content = {
//                Image(
//                    painter = painterResource(id = R.drawable.escudo_barca),
//                    contentDescription = "",
//
//                )
//            },
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Establece el color del botón como transparente
//
//
//
//        )
        Image(painter = painterResource(id = R.drawable.escudo_barca_2), contentDescription ="",modifier=Modifier.clickable {
            viewModel.guardarListaEnFichero(context)
            navController.navigate(route=Screens.Agenda.route)
        } )

        Image(painter = painterResource(id = R.drawable.agenda_barca), contentDescription ="",modifier=Modifier.clickable {
            viewModel.guardarListaEnFichero(context)
            navController.navigate(route=Screens.Agenda.route)
        }.size(220.dp) )
    }

}