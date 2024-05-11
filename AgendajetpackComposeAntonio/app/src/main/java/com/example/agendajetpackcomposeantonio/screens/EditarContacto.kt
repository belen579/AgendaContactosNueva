package com.antonio.agendajetpackcompose.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
fun EditarContacto(navController: NavHostController, viewModel: AgendaViewModel){
    Scaffold(
        topBar = {
            MyTopBar4(navController, viewModel)
        },
        content = { padding ->
            ContenidoEditarContacto(navController, viewModel)
        },


        )
}



@Composable
fun MyTopBar4(
    navController: NavHostController,
    viewModel: AgendaViewModel,
    backgroundColor: Color = Color(10, 48, 100),//azul
    contentColor: Color = Color(232, 18, 36),//rojo
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    val colorRojo = Color(232, 18, 36)
    TopAppBar(

        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = Screens.Agenda.route) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Ir hacia atras",
                    tint = colorRojo,
                    modifier = Modifier.size(60.dp)
                )
            }


        },
        title = {
            Text(
                "Agenda F.C. Barcelona",
                color = colorRojo,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        actions = {

            IconButton(onClick = {
                showDialog=true

            }) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "guardar contacto",
                    tint = colorRojo,
                    modifier = Modifier.size(70.dp)
                )
            }


        },


        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation

    )
    if(showDialog){
        MyDialogEditarContacto(
            onDismiss = { showDialog = false },
            onAccept = {
                viewModel.getFoto(R.drawable.jugadordesconocido)
                var fotoByteArrays = viewModel.obtenerBytesDeDrawable(context, viewModel.foto)

                viewModel.getContactoFinalAux(
                    ContactosFinales(
                        viewModel.contactofinal.id,
                        viewModel.nombre,
                        viewModel.apellidos,
                        viewModel.direccion,
                        viewModel.codigoPostal,
                        viewModel.ciudad,
                        viewModel.provincia,
                        viewModel.telefonoFijo,
                        viewModel.telefonoMovil,
                        viewModel.email,
                        viewModel.cumpleaños,
                        viewModel.observaciones,
                        fotoByteArrays
                    )
                )


                viewModel.editarContactoEnFichero(context, viewModel.contactofinal,viewModel.contactofinalAux)
                showDialog = false
                navController.navigate(route = Screens.Agenda.route)


            }
        )
    }




}

@Composable
fun MyDialogEditarContacto(onDismiss: () -> Unit, onAccept: () -> Unit) {
    val colorRojo = Color(232, 18, 36)
    val colorAzul = Color(10, 48, 100)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Editar Contacto")
        },
        text = {
            Text(text = "¿Estás seguro de que deseas guardar este contacto?")
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContenidoEditarContacto(navController: NavHostController, viewModel: AgendaViewModel) {
    val colorRojo = Color(232, 18, 36)
    val colorAzul = Color(10, 48, 100)
    val colorAmarillo = Color(235, 203, 73)
    viewModel.cargarAtributosContacto()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Card(
            border = BorderStroke(2.dp, colorRojo),
            colors = CardDefaults.cardColors(containerColor = colorAmarillo),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            //  .padding(top=75.dp, bottom = 16.dp, start = 16.dp,end=16.dp)

        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, top = 5.dp)
            ) {
                Column(
                    Modifier
                        .weight(1.2f)
                        .padding(top = 4.dp, start = 10.dp),
                    // .height(160.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                )
                {

                    TextField(
                        value = viewModel.nombre,
                        onValueChange = { viewModel.getNombre(it) },
                        label = {
                            if (viewModel.nombre.isEmpty()) {
                                Text("Nombre", style = TextStyle(fontSize = 14.sp))
                            } else {
                                Text(
                                    "Nombre",
                                    style = TextStyle(fontSize = 12.sp),
                                    color = colorAzul
                                )
                            }
                        },
                        textStyle = TextStyle(fontSize = 16.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier
                            .width(180.dp)
                            .height(55.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.size(2.dp))

                    TextField(
                        value = viewModel.apellidos,
                        onValueChange = { viewModel.getApellidos(it) },
                        label = {
                            if (viewModel.apellidos.isEmpty()) {
                                Text("Apellidos", style = TextStyle(fontSize = 14.sp))
                            } else {
                                Text(
                                    "Apellidos",
                                    style = TextStyle(fontSize = 12.sp),
                                    color = colorAzul
                                )
                            }
                        },
                        textStyle = TextStyle(fontSize = 16.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier
                            .width(180.dp)
                            .height(55.dp),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    TextField(
                        value = viewModel.telefonoFijo,
                        onValueChange = { viewModel.getTelefonoFijo(it) },
                        label = {
                            if (viewModel.telefonoFijo.isEmpty()) {
                                Text("Telefono fijo", style = TextStyle(fontSize = 14.sp))
                            } else {
                                Text(
                                    "Telefono fijo",
                                    style = TextStyle(fontSize = 12.sp),
                                    color = colorAzul
                                )
                            }
                        },
                        textStyle = TextStyle(fontSize = 16.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .width(180.dp)
                            .height(55.dp),
                        singleLine = true
                    )


                    Spacer(modifier = Modifier.size(2.dp))
                    TextField(
                        value = viewModel.telefonoMovil,
                        onValueChange = { viewModel.getTelefonoMovil(it) },
                        label = {
                            if (viewModel.telefonoMovil.isEmpty()) {
                                Text("Telefono movil", style = TextStyle(fontSize = 14.sp))
                            } else {
                                Text(
                                    "Telefono movil",
                                    style = TextStyle(fontSize = 12.sp),
                                    color = colorAzul
                                )
                            }
                        },
                        textStyle = TextStyle(fontSize = 16.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .width(180.dp)
                            .height(55.dp),
                        singleLine = true
                    )


                }
                Column(
                    Modifier
                        .weight(0.9f)
                        .padding(top = 4.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Image(
                        //alignment = Alignment.CenterEnd,
                        painter = painterResource(R.drawable.jugadordesconocido),
                        contentDescription = "foto",


                        )
                    Spacer(modifier = Modifier.size(15.dp))
                    Button(
                        onClick = { },
                        shape = RectangleShape,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorAzul,
                            contentColor = colorRojo
                        )
                    ) {
                        Text(text = "Elegir Foto", fontSize = 18.sp)
                    }
                }


            }
            Spacer(modifier = Modifier.size(2.dp))
            TextField(
                value = viewModel.direccion,
                onValueChange = { viewModel.getDireccion(it) },
                label = {
                    if (viewModel.direccion.isEmpty()) {
                        Text("Dirección", style = TextStyle(fontSize = 14.sp))
                    } else {
                        Text("Dirección", style = TextStyle(fontSize = 12.sp), color = colorAzul)
                    }
                },
                textStyle = TextStyle(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 10.dp),
                singleLine = true
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp, horizontal = 8.dp)
            ) {
                TextField(
                    value = viewModel.codigoPostal,
                    onValueChange = { viewModel.getCodigoPostal(it) },
                    label = {
                        if (viewModel.codigoPostal.isEmpty()) {
                            Text("C.P.", style = TextStyle(fontSize = 14.sp))
                        } else {
                            Text("C.P.", style = TextStyle(fontSize = 12.sp), color = colorAzul)
                        }
                    },
                    textStyle = TextStyle(fontSize = 16.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .weight(0.5f)
                        .height(55.dp)
                        .padding(horizontal = 2.dp),
                    singleLine = true
                )

                TextField(
                    value = viewModel.ciudad,
                    onValueChange = { viewModel.getCiudad(it) },
                    label = {
                        if (viewModel.ciudad.isEmpty()) {
                            Text("Ciudad", style = TextStyle(fontSize = 14.sp))
                        } else {
                            Text("Ciudad", style = TextStyle(fontSize = 12.sp), color = colorAzul)
                        }
                    },
                    textStyle = TextStyle(fontSize = 16.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier
                        .weight(1f)
                        .height(55.dp)
                        .padding(horizontal = 2.dp),
                    singleLine = true
                )
            }

            TextField(
                value = viewModel.provincia,
                onValueChange = { viewModel.getProvincia(it) },
                label = {
                    if (viewModel.provincia.isEmpty()) {
                        Text("Provincia", style = TextStyle(fontSize = 14.sp))
                    } else {
                        Text("Provincia", style = TextStyle(fontSize = 12.sp), color = colorAzul)
                    }
                },
                textStyle = TextStyle(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 10.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.size(2.dp))

            TextField(
                value = viewModel.email,
                onValueChange = { viewModel.getEmail(it) },
                label = {
                    if (viewModel.email.isEmpty()) {
                        Text("email", style = TextStyle(fontSize = 14.sp))
                    } else {
                        Text("email", style = TextStyle(fontSize = 12.sp), color = colorAzul)
                    }
                },
                textStyle = TextStyle(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 10.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.size(2.dp))

            TextField(
                value = viewModel.cumpleaños,
                onValueChange = { viewModel.getCumpleanhos(it) },
                label = {
                    if (viewModel.cumpleaños.isEmpty()) {
                        Text("Cumpleaños", style = TextStyle(fontSize = 14.sp))
                    } else {
                        Text("Cumpleaños", style = TextStyle(fontSize = 12.sp), color = colorAzul)
                    }
                },
                textStyle = TextStyle(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 10.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.size(2.dp))

            TextField(
                value = viewModel.observaciones,
                onValueChange = { viewModel.getObservaciones(it) },
                label = {
                    if (viewModel.observaciones.isEmpty()) {
                        Text("Observaciones", style = TextStyle(fontSize = 14.sp))
                    } else {
                        Text(
                            "Observaciones",
                            style = TextStyle(fontSize = 12.sp),
                            color = colorAzul
                        )
                    }
                },
                textStyle = TextStyle(fontSize = 16.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))


        }

    }
}