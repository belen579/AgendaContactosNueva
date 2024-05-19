package com.example.examen_segunda_ev.ui.theme.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun primeraactivity(viewModel: viewModel) {



    val elementos: List<String> = List(viewModel.numContInt) { "Item ${it}" }




    Scaffold(modifier = Modifier.background(Color.Blue),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceTint,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ), title = { Text(text = ("Examen"))
                },
                actions = {
                    if (viewModel.visible) {
                        IconButton(onClick = viewModel.OnchangeVis) {
                            Icon(Icons.Default.Refresh, contentDescription = null, tint= Color.Magenta)

                        }
                        IconButton(onClick = { viewModel.suma() }) {
                            Icon(Icons.Default.Add, contentDescription = null, tint= Color.Magenta)
                        }
                        IconButton(onClick = { viewModel.resta() }) {
                            Icon(Icons.Default.Delete, contentDescription = null, tint= Color.Magenta)
                        }
                    }

                })

        }
    ) {


        Column(


            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        ) {
            if (!viewModel.visible) {
                TextField(
                    value = viewModel.numCont,
                    onValueChange = {viewModel.onContChange (it) },
                    label = { Text(text = "Numero de contadores") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Button(onClick = viewModel.onConvert) {
                    Text(text = "Acceder")
                }
                Spacer(modifier = Modifier.padding(15.dp))
            }
            if (viewModel.visible) {

                LazyColumn {
                    items(elementos) { el ->
                        LazyColumnelementos(elemento = el)
                    }
                }

            }
        }
    }
}
