package com.example.ex_terceraev.ui.Screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ex_terceraev.ui.Data.Producto

class ProductoViewModel: ViewModel() {


    var checkproducto by mutableStateOf(false)


    var nombre by mutableStateOf("")


    var total by mutableStateOf(0.0)

    var checkall by mutableStateOf(false)

    var listaproductoseleccionado = mutableStateListOf<Producto>()

  //  var lista = mutableStateListOf<Producto>()

/*
    init{
        crearListaDeProductos()
    }
*/

    val productos:List<Producto>
    get() {
        // Esto puede ser List o listOf ()

          /*  return List(20) { index ->
                Producto("Producto ${index + 1}", 0.0,false)
            }*/
        return listOf(


            Producto("Raton", 20.0, false),
            Producto("Pantalla", 80.0,false),
            Producto("RAM", 50.0,false),
            Producto("CPU", 300.0,false),
            Producto("Camara", 20.0,false),
            Producto("Tarjeta Grafica", 100.0,false),
            Producto("Batería", 70.0,false) ,
            Producto("CPU", 300.0,false),
            Producto("Television", 20.0,false),
            Producto("Antena", 100.0,false),
            Producto("Radio", 70.0,false)
        )
    }
/*
    fun crearListaDeProductos(): List<Producto> {


        for (i in 1..30) {
            lista.add(Producto("Producto $i", precio = (i * 10).toDouble()))
        }

        return lista
    }*/



    fun sumarproducto(producto: Producto){

        total+= producto.precio;



    }

    fun restarproducto(producto: Producto){

        total-= producto.precio;



    }


    fun seleccionartodosloscheck(productos:List<Producto>){

        listaproductoseleccionado.addAll(productos)
        total = listaproductoseleccionado.sumOf { it.precio }
        println(listaproductoseleccionado.joinToString())

    }

    fun cleartodosloscheck(){

            listaproductoseleccionado.removeAll(productos)
        total =0.0
        println(listaproductoseleccionado.joinToString())
    }

}





