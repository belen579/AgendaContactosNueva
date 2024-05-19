package com.example.ejerciciobotones.ui.theme
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@Composable
fun BotonesNuevos(){
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        botonuno()
    }
}

@Preview(showBackground = true)
@Composable
fun botonuno(){
    val context = LocalContext.current
    Button(
        onClick = {
            Toast.makeText(context, "Ha echo click", Toast.LENGTH_SHORT).show()
        }



    ) {
        Text("Botón Estilo Primario", color = Color.White)
    }



}


@Preview(showBackground = true)
@Composable
fun botondos(){
    val context = LocalContext.current
    Button(

        onClick = {
            Toast.makeText(context, "Ha echo click", Toast.LENGTH_SHORT).show()
        },

        modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color.Magenta),
                colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colorScheme.secondary


    ) )

    {
        Text("Botón en Text", color = Color.Black)
    }




}





