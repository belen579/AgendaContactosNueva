package com.example.compra_productos_tecnologicos.ui.Screens

import android.annotation.SuppressLint


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues


import androidx.compose.foundation.layout.Row


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.productos_tecnologicos.ui.screens.Producto
import com.example.productos_tecnologicos.ui.screens.ViewModel_Producto



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun presupuesto(viewmodel: ViewModel_Producto) {
    val scrollState = rememberLazyListState()
    Column(
        modifier= Modifier.padding(top =50.dp)

    ) {
        Text(
            text = "Total: ${viewmodel.total}€",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 8.dp),
                    state = scrollState
        ) {
            items(viewmodel.productos) { producto ->
                checkbox(producto, viewmodel)
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

    }
}






@SuppressLint("RestrictedApi")
@Composable
fun checkbox (producto: Producto, viewmodel: ViewModel_Producto) {
    var checkedstate by rememberSaveable { mutableStateOf(producto.seleccionado) }


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
                Checkbox(
                    checked = checkedstate,
                    onCheckedChange = { isChecked ->
                        checkedstate = isChecked

                        if (isChecked) {
                            viewmodel.sumaproducto(producto)
                            viewmodel.contador()

                        } else {
                            viewmodel.restaproducto(producto)
                            viewmodel.contadormenos()
                        }
                    },
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(text = "${producto.nombre} - ${producto.precio}€", style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(30.dp))


                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Image(
                        painter = painterResource(id = producto.imagen), // Reemplaza "imagen_producto" con el ID de tu imagen
                        contentDescription = null, // Descripción opcional para accesibilidad
                        modifier = Modifier.fillMaxWidth().height(180.dp),
                    )

                }

            }
//


        }


    }




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable()
fun previewcomposable(viewmodel: ViewModel_Producto) {
    var scaffoldBackgroundColor by remember { mutableStateOf(Color.Blue) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors= TopAppBarDefaults.mediumTopAppBarColors (containerColor= MaterialTheme.colorScheme.primary),
                title = { Text(text = "Presupuesto") },

                actions = {
                    IconButton(onClick = {viewmodel.total}) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color.Black)
                    }
                    Text(text = "Items en el carrito ${viewmodel.item}")
                }
            )
        }, contentColor = scaffoldBackgroundColor
    )

    {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
        ) {
            presupuesto(viewmodel)
        }
    }
}

























