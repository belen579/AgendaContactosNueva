package com.example.productos_tecnologicos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.productos_tecnologicos.R

@Composable
fun portadaviewModel(viewmodelProducto: ViewModel_Producto) {


    Column(modifier = Modifier.fillMaxWidth(), Arrangement.Center, Alignment.CenterHorizontally) {



        Image( painter = painterResource(id = R.drawable.logotipo), // Reemplaza "imagen_producto" con el ID de tu imagen
            contentDescription = null, // Descripción opcional para accesibilidad
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp))




        TextField(
            value = viewmodelProducto.email,
            onValueChange = { viewmodelProducto.email = it },
            label = {
                Text(
                    text = "Email"
                )
            })
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = viewmodelProducto.email,
            onValueChange = { viewmodelProducto.email = it },
            label = {
                Text(
                    text = "Contraseña"
                )
            })


        Button(onClick = { },   colors= ButtonDefaults.buttonColors(Color(255, 193, 7, 255))) {




             Text(text = "Acceder")
        }


    }
}



@Composable
@Preview
fun previewportada(){
    var viewmodelProducto: ViewModel_Producto = ViewModel_Producto()


    portadaviewModel( viewmodelProducto )
}