package com.example.compra_productos_tecnologicos.ui.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.TypedArrayUtils.getResourceId
import com.example.compra_productos_tecnologicos.R
import com.example.compra_productos_tecnologicos.ui.Screens.Inicializarproductos.productos

object Inicializarproductos {
    val productos: List<Producto>
        @Composable get() {
            return listOf(
                Producto("Raton", 20.0, false, R.drawable.raton),
                Producto("Pantalla", 80.0,false, R.drawable.pantalla),
                Producto("RAM", 50.0,false, R.drawable.ram),
                Producto("CPU", 300.0,false, R.drawable.cpu),
                Producto("Camara", 20.0,false, R.drawable.camara),
                Producto("Tarjeta Grafica", 100.0,false, R.drawable.tarjetagrafica),
                Producto("Batería", 70.0,false, R.drawable.bateria)
            )
        }
}






@Composable
fun presupuesto(productos:List<Producto>){

    var total by rememberSaveable {
        mutableStateOf(0.0)
    }



    Column {
        LazyColumn{
            items(productos){
                producto->
                checkbox(producto ){
                    isChecked-> producto.seleccionado= isChecked
                if(isChecked){
                    total += producto.precio
                }else{
                    total -= producto.precio
                }

            }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Total: $total€",
            modifier = Modifier.align(Alignment.CenterHorizontally),

        )
    }






}


@SuppressLint("RestrictedApi")
@Composable
fun checkbox (producto: Producto, onCheckedChange:(Boolean)->Unit) {


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = producto.seleccionado,
            onCheckedChange = { isChecked -> onCheckedChange(isChecked) },
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(text = "${producto.nombre} - ${producto.precio}€")
        Spacer(modifier = Modifier.width(30.dp))


         Column(horizontalAlignment = Alignment.CenterHorizontally,
             modifier = Modifier.fillMaxHeight()){
             Image(
                 painter = painterResource(id =  producto.imagen), // Reemplaza "imagen_producto" con el ID de tu imagen
                 contentDescription = null, // Descripción opcional para accesibilidad
                 modifier =  Modifier.size(64.dp),

             )

         }

        }


    }


@Preview()
@Composable()
fun previewcomposable(){
    presupuesto(productos)
}