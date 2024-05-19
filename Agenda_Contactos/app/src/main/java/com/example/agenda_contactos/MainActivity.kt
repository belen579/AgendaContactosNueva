package com.example.agenda_contactos

import android.R
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.agenda_contactos.ui.persistance.Contacto
import com.example.agenda_contactos.ui.theme.Agenda_ContactosTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Agenda_ContactosTheme {

                val listacontacto:List<Contacto>
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AdministrarContactos()

                 //   leerDatos()

                }
            }
        }
    }


    fun leerDatos() {
        val file = File( "contactos.txt")
        if (file.exists()) {
            val lista = file.readLines()
            var indice = 0
            while (indice < lista.size) {
                contactos.add(Contacto(lista.get(indice), lista.get(indice + 1)))
                indice += 2
            }
        }
    }
}



val contactos = mutableStateListOf<Contacto>()


@Composable
fun AdministrarContactos() {
    val activity = LocalContext.current

    var nombre by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    Column() {
        OutlinedTextField(value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de contacto") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        OutlinedTextField(value = mail,
            onValueChange = { mail = it },
            label = { Text("telefono") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        Button(onClick = {
            val nuevoContacto = Contacto(nombre, mail)
            contactos.add(nuevoContacto)
            val path = activity.getFilesDir()
            val file = File(path, "contactos.txt")
            file.appendText("${nombre}\n${mail}\n")
            nombre = ""
            mail = ""
        }) {
            Text(text = "Agregar", modifier = Modifier.fillMaxWidth())
        }
        LazyColumn() {
            itemsIndexed(contactos) { indice, contacto ->
                MostrarContacto(indice, contacto)
            }
        }
    }
}



@Composable
fun MostrarContacto(indice: Int, contacto: Contacto) {
    val context = LocalContext.current
    Text(text = contacto.nombre)
    Text(text = contacto.numero)
    Image(painter = painterResource(id = R.drawable.ic_delete), contentDescription = "",
        modifier = Modifier.clickable {
            contactos.removeAt(indice)
            grabarCambios(context)
        })
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .width(4.dp), color = Color.Black
    )
}

fun grabarCambios(context: Context) {
    val file = File(context.filesDir, "contactos.txt")

    for (contacto in contactos) {
        file.appendText("${contacto.nombre}\n${contacto.numero}\n")
    }
}


