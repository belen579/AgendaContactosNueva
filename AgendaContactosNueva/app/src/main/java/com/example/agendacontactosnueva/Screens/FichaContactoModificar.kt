package com.example.agendacontactos_objetos.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendacontactos_objetos.Data.Contacto
import com.example.agendacontactos_objetos.Data.viewModelContacto
import com.example.agendacontactosnueva.R
import com.example.agendacontactos_objetos.navigation.Screens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation",
    "UnrememberedMutableState"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun fichacontactoModificar(viewModel: viewModelContacto, navController: NavController, idusuario: String?) {

    println("Este es el id $idusuario")



    var idint = idusuario?.toInt()

    val contacto = idint?.let { viewModel.obtenerContactoPorId(it) }
    var nombrerecibido2 by mutableStateOf(contacto?.nombre.toString())
    var emailrecibido by mutableStateOf(contacto?.email.toString())
    var telefonorecibido by mutableStateOf(contacto?.telefono.toString())





    var context = LocalContext.current

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Contacto") },
                actions = {
                    IconButton(onClick = {

                        val contactomodificado = Contacto(
                            nombrerecibido2,
                            telefonorecibido,
                            emailrecibido,
                            R.drawable.neutra,
                            viewModel.nextid
                        )
                        viewModel.guardarcontactoarchivo(
                            contactomodificado
                        )
                        if (contacto != null) {
                            viewModel.borrar(contacto)
                        }



                        navController.navigate(route = Screens.inicio.route)
                    }) {
                        Icon(
                            Icons.Default.Save,
                            contentDescription = "Guardar",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }


            )

        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(id = R.drawable.neutra),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.AccountCircle, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))

                OutlinedTextField(

                    value = nombrerecibido2,
                    onValueChange = { nombrerecibido2 = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
            }





            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Call, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = telefonorecibido,
                    onValueChange = { telefonorecibido = it },
                    label = { Text("Telefono") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Email, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = emailrecibido,
                    onValueChange = { emailrecibido = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }





    }
}



