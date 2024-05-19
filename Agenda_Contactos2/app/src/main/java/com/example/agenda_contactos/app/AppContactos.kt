package com.example.agenda_contactos.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agenda_contactos.data.Contacto
import com.example.agenda_contactos.data.viewModelcontacto
import java.io.File



@SuppressLint("UnrememberedMutableState", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appcontactos(viewModel: viewModelcontacto){




    var nombre:String by rememberSaveable { mutableStateOf("") }
    var telefono:String  by rememberSaveable { mutableStateOf("") }

    var contactos = mutableStateListOf<Contacto>()

    val file = File("res/raw/contactos.txt")



    
    Column(modifier = Modifier
        .padding(16.dp)
        .padding(top = 60.dp) ) {


        OutlinedTextField(value = nombre, onValueChange ={nombre= it},
            label = { Text(text = "Nombre")}, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.padding(2.dp))

        OutlinedTextField(value = telefono, onValueChange ={telefono= it},
            label = { Text(text = "Telefono")}, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.padding(2.dp))
        Button(onClick = { viewModel.agregarContacto(Contacto(nombre, telefono))
       contactos.add(Contacto(nombre, telefono))
            nombre = ""
            telefono=""

        }) {
            Text(text = "Agregar Contacto")

        }

        Spacer(modifier = Modifier.height(2.dp))
        eliminarcontacto(viewModel)



        
    }
    
}
@Composable
fun eliminarcontacto(viewModel: viewModelcontacto) {
     val contactos = viewModel.contactos

    LazyColumn() {
        items(contactos) { contacto ->
            ContactoItem(contacto = contacto,
                OnDeleteclick = { viewModel.eliminarContacto(contacto) },
                OnUpdateclick = { viewModel.modificarContacto(contacto) }
            )
        }
    }


}

@Composable
fun ContactoItem(contacto: Contacto, OnDeleteclick: () -> Unit, OnUpdateclick:()->Unit) {


    Card(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth()){

        Row(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            Column {
                Text(text = "Nombre: ${contacto.nombre}")
                Text(text = "Teléfono: ${contacto.telefono}")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = OnDeleteclick) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }

            IconButton(onClick = OnUpdateclick) {
                Icon(Icons.Default.Edit, contentDescription = "Editar")
            }

        }



}

}





@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun previewcontacto(){
    val context = LocalContext.current
    var view = viewModelcontacto(context)

    Scaffold (
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                title = { Text("Agenda de Contactos") },
            )
        }
    ){
        Appcontactos(view)
    }

}