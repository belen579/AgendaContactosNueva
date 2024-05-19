package com.example.gestion_login.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class  viewModelUsuario:ViewModel(){


    var user: String by mutableStateOf("");

    var password:String  by mutableStateOf("")


    var contadorpassword :Int by mutableStateOf(3)


    val users= listOf<User>(
        User("Juan", "123456") ,
        User("Ana", "123456"),
        User("Francisco", "123456"),
        User("Pablo", "123456"),
        User("Antonio", "123456")

    )

    @SuppressLint("SuspiciousIndentation")
    fun comprobarpassword(user: String, passwordimput:String):Boolean{
     var passwordCorrecta= false

        users.forEach{
            userstored->
            if(userstored.user== user &&  userstored.password== passwordimput ){
                passwordCorrecta=   true
            }
        }

        return passwordCorrecta
    }

    fun contadorintentos(context: Context):Int{

        contadorpassword--

        if(contadorpassword==0){
            val toast =  Toast.makeText(context, "no tiene intentos, intentelo dentro de 10 minutos ", Toast.LENGTH_SHORT)
            toast.show()
        }


        return contadorpassword

    }










}