package com.antonio.agendajetpackcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomAppBar
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.antonio.agendajetpackcompose.R
import com.antonio.agendajetpackcompose.ui.model.ContactosFinales
import com.antonio.agendajetpackcompose.ui.navigation.Screens
import com.antonio.agendajetpackcompose.ui.viewmodel.AgendaViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaDetalle(navController: NavHostController, viewModel: AgendaViewModel) {

    Scaffold(
        topBar = {
            MyTopBar2(navController,viewModel)
        },
        content = {padding ->
            ContenidoDetalle(navController, viewModel)
        },
        bottomBar = {
            MyBottonBar(navController,viewModel)
        }

    )




}

@Composable
fun MyBottonBar(navController: NavHostController,
                viewModel: AgendaViewModel,
                backgroundColor: Color = Color(10, 48, 100),//azul
                contentColor: Color = Color(232, 18, 36),//rojo
                elevation: Dp = AppBarDefaults.BottomAppBarElevation
                ) {

    val colorRojo=Color(232, 18, 36)
    var context= LocalContext.current

    BottomAppBar(content = {
        Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
            IconButton(onClick = {
                viewModel.IrInicio()
                navController.navigate(route = Screens.AgendaDetalle.route)

            }) {
                Icon(imageVector =Icons.Filled.SkipPrevious , contentDescription ="", tint = colorRojo )
            }

            IconButton(onClick = {
                if(viewModel.contactofinal==viewModel.listaContactosLeidos.first()){
                    Toast.makeText(context,"No hay mas registros", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.IrAtras()
                    navController.navigate(route = Screens.AgendaDetalle.route)
                }


             }) {
                Icon(imageVector =Icons.Filled.ArrowLeft , contentDescription ="", tint = colorRojo,
                    modifier = Modifier.size(55.dp)
                )
            }

            IconButton(onClick = {
                if(viewModel.contactofinal==viewModel.listaContactosLeidos.last()){
                    Toast.makeText(context,"No hay mas registros", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.IrDelante()
                    navController.navigate(route = Screens.AgendaDetalle.route)
                }

            }) {
                Icon(imageVector =Icons.Filled.ArrowRight , contentDescription ="", tint = colorRojo,
                    modifier = Modifier.size(55.dp) )
            }

            IconButton(onClick = {
                viewModel.IrFinal()
                navController.navigate(route = Screens.AgendaDetalle.route)
            }) {
                Icon(imageVector =Icons.Filled.SkipNext , contentDescription ="", tint = colorRojo )
            }
        }



    },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    )


}

@Composable
fun MyTopBar2(navController: NavHostController,
              viewModel: AgendaViewModel,
              backgroundColor: Color = Color(10, 48, 100),//azul
              contentColor: Color = Color(232, 18, 36),//rojo
              elevation: Dp = AppBarDefaults.TopAppBarElevation
              ) {
    val context= LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val colorRojo=Color(232, 18, 36)
    TopAppBar(

        navigationIcon = {
            IconButton(onClick = {navController.navigate(route = Screens.Agenda.route)}) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Ir hacia atras",
                    tint = colorRojo,
                    modifier = Modifier.size(60.dp)
                )
            }



        },
        title = { Text("Agenda F.C. Barcelona", color = colorRojo, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
        actions = {
            IconButton(onClick = {
                showDialog=true

            }) {
                Icon(imageVector = Icons.Filled.Delete,
                    contentDescription = "eliminar contacto",
                    tint = colorRojo,
                    modifier = Modifier.size(30.dp)
                )
            }

            IconButton(onClick = {
                viewModel.setaContactoFinales(viewModel.contactofinal)
                navController.navigate(route = Screens.EditarContacto.route)


            }) {
                Icon(imageVector = Icons.Filled.Edit,
                    contentDescription = "editar contacto",
                    tint = colorRojo,
                    modifier = Modifier.size(30.dp)
                )
            }
        },

        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation

    )
    if(showDialog){
        MyDialogBorrarContacto(
            onDismiss = { showDialog = false },
            onAccept = {
                viewModel.borrarContacto(context, viewModel.contactofinal)
                navController.navigate(route=Screens.Agenda.route)



            }
        )
    }


}

@Composable
fun MyDialogBorrarContacto(onDismiss: () -> Unit, onAccept: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Borrar Contacto")
        },
        text = {
            Text(text = "¿Estás seguro de que deseas Borrar este contacto?")
        },
        confirmButton = {
            Button(
                onClick = {
                    onAccept()
                }
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun ContenidoDetalle(navController: NavHostController, viewModel: AgendaViewModel) {
    val colorRojo = Color(232, 18, 36)
    val colorAzul = Color(10, 48, 100)
    val colorAmarillo = Color(235, 203, 73)
    val imageBitmap= viewModel.contactofinal.foto?.let { viewModel.ByteArrayToImage(it) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Card(
            border = BorderStroke(2.dp, colorRojo),
            colors= CardDefaults.cardColors(containerColor = colorAmarillo),
            modifier = Modifier
                .fillMaxSize()

                .padding(vertical = 75.dp, horizontal = 16.dp)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()

                    .padding(end = 10.dp, top = 10.dp)
            ) {
                Column(
                    Modifier
                        .weight(1f)

                        .padding(top = 30.dp, start = 10.dp)
                        .height(160.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                )
                {


                    Text(
                        text = viewModel.contactofinal.nombre,
                        color = colorRojo,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = viewModel.contactofinal.apellidos,
                        color = colorAzul,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row() {
                        Icon(
                            imageVector = Icons.Filled.Call,
                            contentDescription = "telefonoFijo",
                            tint = colorRojo,
                            modifier = Modifier.size(30.dp)
                        )
                        Text(text = viewModel.contactofinal.telefonoFijo, fontSize = 20.sp)
                    }
                    Row() {
                        Icon(
                            imageVector = Icons.Filled.PhoneAndroid,
                            contentDescription = "telefonoMovil",
                            tint = colorAzul,
                            modifier = Modifier.size(30.dp)
                        )
                        Text(
                            text = viewModel.contactofinal.telefonoMovil,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }

                }
                imageBitmap?.let {
                    Image(
                        alignment = Alignment.CenterEnd,
                        bitmap = it,
                        contentDescription = "foto",
                        modifier = Modifier
                            .size(200.dp)
                            .weight(1f)

                    )
                }

            }
            Row(){
                Text(text = "Dirección: ",
                    modifier=Modifier.padding(start=10.dp,top=10.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = viewModel.contactofinal.Direccion,
                    modifier = Modifier.padding(top=10.dp),
                    fontSize = 16.sp
                )
            }

            Row(){
                Text(text = "C.P: ",
                    modifier=Modifier.padding(start=10.dp,top=10.dp),
                    fontWeight = FontWeight.Bold)
                Text(
                    text = viewModel.contactofinal.codigoPostal,
                    modifier = Modifier.padding(top=10.dp),
                    fontSize = 16.sp
                )

                Text(text = "Ciudad: ",
                    modifier=Modifier.padding(start=10.dp,top=10.dp),
                    fontWeight = FontWeight.Bold)
                Text(
                    text = viewModel.contactofinal.ciudad,
                    modifier = Modifier.padding(top=10.dp),
                    fontSize = 16.sp
                )


            }
            Row(){
                Text(text = "Provincia: ",
                    modifier=Modifier.padding(start=10.dp,top=10.dp),
                    fontWeight = FontWeight.Bold)
                Text(
                    text = viewModel.contactofinal.provincia,
                    modifier = Modifier.padding(top=10.dp),
                    fontSize = 16.sp
                )
            }

            Row(){
                Text(text = "email: ",
                    modifier=Modifier.padding(start=10.dp,top=10.dp),
                    fontWeight = FontWeight.Bold)
                Text(
                    text = viewModel.contactofinal.email,
                    modifier = Modifier.padding(top=10.dp),
                    fontSize = 16.sp
                )
            }

            Row(){
                Text(text = "Cumpleaños: ",
                    modifier=Modifier.padding(start=10.dp,top=10.dp),
                    fontWeight = FontWeight.Bold)
                Text(
                    text = viewModel.contactofinal.cumpleaños,
                    modifier = Modifier.padding(top=10.dp),
                    fontSize = 16.sp
                )
            }

            Card(
                border = BorderStroke(2.dp, colorAzul), modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Column(){
                    Text(text = "Observaciones: ",
                        modifier=Modifier.padding(start=10.dp,top=10.dp),
                        fontWeight = FontWeight.Bold)
                    Text(
                        text = viewModel.contactofinal.observaciones,
                        modifier = Modifier.padding(10.dp)
                    )
                }


            }






           
        }

    }
}
