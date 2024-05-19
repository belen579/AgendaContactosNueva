package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun main(args: Array<String>) {
            var parar = false;
            var contador=1

            do{
                println("Digame cuantos ingredientes quiere para su pizza")
                var numerodeingredientes = Integer.parseInt(readln())
                println("Digame los nombres de los ingredientes")
                var ingrediente = readln()
                fun contador():Int{
                    return   contador++
                }
                val pedido = Pedido.pedido(1, listOf(Pedido.BasePizza()), listOf(Pedido.Ingrediente(ingrediente, numerodeingredientes)))



                println("Numero de pedido ${contador()}")
                println(pedido)

                println("Quiere realizar otro pedido, Diga si o no para continuar")
                var si_no= readln()

                if (!si_no.equals("no") && !si_no.equals("No") && !si_no.equals("NO")){
                    parar=true

                }else{
                    parar= false
                    println("Que disfrute de su pedido, Hasta pronto")
                }

            }while (parar)
        }


    }
        }




