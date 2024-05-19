package com.example.agendacontactosnueva

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddReaction
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.agendacontactos_objetos.Data.Contacto
import com.example.agendacontactos_objetos.Data.viewModelContacto
import com.example.agendacontactos_objetos.navigation.Navigation
import com.example.agendacontactos_objetos.navigation.Screens
import com.example.agendacontactosnueva.ui.theme.AgendaContactosNuevaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgendaContactosNuevaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()
                    Navigation()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun agendacontactos(viewModelContacto: viewModelContacto, navController: NavController){
    val list by rememberSaveable { mutableStateOf(viewModelContacto.list) }
    /*
        LaunchedEffect(Unit) {
            viewModelContacto.list.addAll(viewModelContacto.cargarContactos())
        }*/

    Column(modifier= Modifier
        .fillMaxWidth()
        .padding(top = 70.dp)) {



        LazyColumn{
            items(viewModelContacto.list){
                    contacto->
                //println(contacto)
                contactocard(contacto,viewModelContacto,navController)
            }
        }

    }



}




@Composable()
fun contactocard(
    contacto: Contacto,
    viewModelContacto: viewModelContacto,
    navController: NavController
) {


    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),


        ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(id = contacto.foto), // Reemplaza "imagen_producto" con el ID de tu imagen
                contentDescription = null, modifier = Modifier.height(26.dp) // Descripción opcional para accesibilidad

            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = "${contacto.nombre}", style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = "${contacto.telefono}", style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )


            IconButton(onClick = {
                viewModelContacto.borrar(contacto)

                viewModelContacto.list.remove(contacto)
                navController.navigate(route = Screens.inicio.route)

            }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
            IconButton(onClick = {

                viewModelContacto.modificar(contacto)
                viewModelContacto.viewModelScope.launch {


                    navController.navigate(route = Screens.fichamodificar.crearRoute(contacto.id.toString()))
                }








            }) {
                Icon(Icons.Default.Edit, contentDescription = "Modificar")
            }


        }


    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun preview(navController: NavController) {
    val context = LocalContext.current
    val viewModelContacto =  (viewModelContacto(context))

    val contacto: Contacto = Contacto("", "", "", R.drawable.neutra, 0)




    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Agenda") },
                actions={
                    Text(text = "Añadir contacto")
                    IconButton(onClick = {navController.navigate(route= Screens.ficha.route)}) {
                        Icon(Icons.Default.AddReaction, contentDescription = "Guardar",modifier = Modifier.size(32.dp))
                    }
                }


            )

        }
    ) {
        // fichacontacto(contacto)
        agendacontactos(viewModelContacto,navController)
    }




}