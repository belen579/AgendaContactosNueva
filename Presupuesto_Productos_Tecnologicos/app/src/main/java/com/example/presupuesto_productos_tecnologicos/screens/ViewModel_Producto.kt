package com.example.productos_tecnologicos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.productos_tecnologicos.ui.screens.Producto
import com.example.productos_tecnologicos.R


public final class ViewModel_Producto: ViewModel(){


    var total by mutableStateOf(0.0)
    private  set

    var item by mutableStateOf(0)
    private set


    var email by mutableStateOf("")
    var contrasena by mutableStateOf("")







    val productos: List<Producto>
         get() {
            return listOf(
                Producto("Raton", 20.0, false, R.drawable.raton),
                Producto("Pantalla", 80.0,false, R.drawable.pantalla),
                Producto("RAM", 50.0,false, R.drawable.ram),
                Producto("CPU", 300.0,false, R.drawable.cpu),
                Producto("Camara", 20.0,false, R.drawable.camara),
                Producto("Tarjeta Grafica", 100.0,false, R.drawable.tarjetagrafica),
                Producto("Batería", 70.0,false, R.drawable.bateria) ,
                        Producto("CPU", 300.0,false, R.drawable.cpu),
             Producto("Camara", 20.0,false, R.drawable.camara),
             Producto("Tarjeta Grafica", 100.0,false, R.drawable.tarjetagrafica),
             Producto("Batería", 70.0,false, R.drawable.bateria)
            )
        }


    fun sumaproducto(producto: Producto){

        total+= producto.precio

    }


    fun restaproducto(producto: Producto){

        total-=producto.precio
    }


    fun contador(){
        item++
    }
    fun contadormenos(){
        item--
    }

    var checkedstate by mutableStateOf(false)







}



