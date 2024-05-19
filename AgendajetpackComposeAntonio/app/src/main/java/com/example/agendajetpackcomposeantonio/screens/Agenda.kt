package com.antonio.agendajetpackcompose.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ButtonColors
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.antonio.agendajetpackcompose.ui.model.Contactos
import com.antonio.agendajetpackcompose.ui.model.ContactosFinales
import com.antonio.agendajetpackcompose.ui.navigation.Screens
import com.antonio.agendajetpackcompose.ui.viewmodel.AgendaViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Agenda(navController: NavHostController, viewModel: AgendaViewModel) {

    Scaffold(
        topBar = {
            MyTopBar(navController,viewModel)
        },
        content = {
            Contenido(navController,viewModel)
        }
    )





}

@Composable
fun MyTopBar(navController: NavHostController,
             viewModel: AgendaViewModel,
             backgroundColor: Color = Color(10, 48, 100),//azul
             contentColor: Color = Color(232, 18, 36),//rojo
          //   elevation: Dp = AppBarDefaults.TopAppBarElevation
             ) {
    val context= LocalContext.current

    val colorRojo=Color(232, 18, 36)
    TopAppBar(

        navigationIcon = {
            IconButton(onClick = {navController.navigate(route = Screens.Menu.route)}) {
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
                navController.navigate(route=Screens.AgendaAnhadirContacto.route)

            }) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "añadir contacto",
                    tint = colorRojo,
                    modifier = Modifier.size(80.dp)
                )
            }





        },
        backgroundColor = backgroundColor,
        contentColor = contentColor,
      //  elevation = elevation

    )


}

@Composable
fun Contenido(navController: NavHostController, viewModel: AgendaViewModel) {

    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 60.dp)
    ) {
        items(viewModel.leerContactosArchivo(context)) {

            ItemContactos(
                navController = navController,
                viewModel = viewModel,
                contacto = it,

            ) {
                viewModel.setaContactoFinales(it)
                navController.navigate(route = Screens.AgendaDetalle.route)
            }
        }
    }


}

@Composable
fun ItemContactos(navController: NavHostController,viewModel: AgendaViewModel, contacto: ContactosFinales, onItemSelected: () -> Unit) {
    var context= LocalContext.current
    val colorRojo=Color(232, 18, 36)
    val colorAzul=Color(10, 48, 100)
    var showDialog by remember { mutableStateOf(false) }
    val imageBitmap = if (contacto.foto != null) {
        viewModel.ByteArrayToImage(contacto.foto!!)
    } else {
        null
    }
    //val imageBitmap= contacto.foto?.let { viewModel.ByteArrayToImage(it) }
    //se transforma ByteArray a imageBitMap para poder utilizar la foto en el Image()

    if(contacto.id!=0) {
        Card(border = BorderStroke(2.dp, colorRojo), modifier = Modifier
            .fillMaxWidth()

            .clickable { onItemSelected() }
            .padding(horizontal = 8.dp, vertical = 2.dp)) {

            Row() {
                imageBitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "foto contacto",
                        modifier = Modifier.size(90.dp)
                    )
                }
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(
                        text = contacto.nombre + " " + contacto.apellidos,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(contentAlignment = Alignment.CenterStart){
                            Icon(
                                imageVector = Icons.Filled.Call,
                                contentDescription = "telefonoFijo",
                                tint = colorRojo,

                                )
                        }

                        Box(contentAlignment = Alignment.CenterStart){
                            Text(text = contacto.telefonoFijo, fontSize = 18.sp)
                        }

                        Box(contentAlignment = Alignment.CenterEnd){
                            IconButton(onClick = {
                                showDialog=true

                            }, modifier = Modifier.padding(start = 120.dp)) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Borrar",
                                    tint = Color.Black,
                                    //modifier = Modifier.align(alignment = Alignment.End)

                                )
                            }
                        }



                    }
                    if(showDialog){
                        MyDialogBorrarContacto2(
                            onDismiss = { showDialog = false },
                            onAccept = {
                                viewModel.setaContactoFinales(contacto)
                                viewModel.borrarContacto(context, viewModel.contactofinal)
                                navController.navigate(route = Screens.Agenda.route)



                            }
                        )
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(contentAlignment = Alignment.CenterStart){
                            Icon(
                                imageVector = Icons.Filled.PhoneAndroid,
                                contentDescription = "telefonoMovil",
                                tint = colorAzul
                            )
                        }
                        Box(contentAlignment = Alignment.CenterStart){
                            Text(text = contacto.telefonoMovil, fontSize = 18.sp)
                        }


                        Box(contentAlignment = Alignment.CenterEnd){
                            IconButton(
                                onClick = {
                                    viewModel.setaContactoFinales(contacto)
                                    navController.navigate(route = Screens.EditarContacto.route)
                                },
                                modifier = Modifier.padding(start = 120.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "Editar",
                                    tint = Color.Black,

                                    )
                            }
                        }

                    }


                }
            }

        }
    }
}

@Composable
fun MyDialogBorrarContacto2(onDismiss: () -> Unit, onAccept: () -> Unit) {
    val colorRojo=Color(232, 18, 36)
    val colorAzul=Color(10, 48, 100)
    val colorAmarillo = Color(235, 203, 73)
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
                },
                colors = ButtonDefaults.buttonColors(containerColor =colorRojo),
                shape = RectangleShape

            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor =colorAzul),
                shape = RectangleShape
            ) {
                Text("Cancelar")
            }
        }
    )
}
