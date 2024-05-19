package com.example.agenda_contactos.ui.persistance

import android.annotation.SuppressLint
import androidx.compose.material3.TopAppBar


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ContactoList(contactos: List<Contacto>, eliminarContacto: (Contacto) -> Unit) {
    LazyColumn {
        items(contactos) { contacto ->
            ContactoItem(contacto = contacto, { eliminarContacto(contacto) })
        }
    }
}




@Composable
fun ContactoItem(contacto: Int, onDeleteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            text= contacto.nombre
            Text(text = contacto.numero)
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}

@Composable
fun AgregarContacto(viewModel: ContactoViewModel) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { ("Teléfono".toString()) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { ("Teléfono") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                viewModel.agregarContacto(Contacto(nombre, telefono))
                nombre = ""
                telefono = ""
            }),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.agregarContacto(Contacto(nombre, telefono))
                nombre = ""
                telefono = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text= "Agregar"))
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactosApp(viewModel: ContactoViewModel) {
    val contactos = remember (viewModel.contactos.value) {
        viewModel.contactos.value?: emptyList()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { ("Agenda de Contactos") },

            )
        },
        content = {
            Column(modifier = Modifier.padding(16.dp)) {
                AgregarContacto(viewModel = viewModel)
                Spacer(modifier = Modifier.height(16.dp))
                ContactoList(contactos = contactos.listIterator(), eliminarContacto = viewModel::eliminarContacto)
            }
        }
    )
}




@Preview
@Composable
fun PreviewContactosApp() {
    val viewModel = ContactoViewModel(ContactoRepositoryImplementacion(LocalContext.current))
    ContactosApp(viewModel)
}