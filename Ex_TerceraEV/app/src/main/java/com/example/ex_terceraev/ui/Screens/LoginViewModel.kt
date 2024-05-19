package com.example.ex_terceraev.ui.Screens

import android.app.AlertDialog
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import com.example.ex_terceraev.ui.Data.Usuario
import java.io.File


class LoginViewModel(val context: Context) :ViewModel() {

    var showDialog = mutableStateOf(false)
    var usuario by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var usuarios: List<Usuario> by mutableStateOf(emptyList())


    var banderapassword by mutableStateOf(false)


    fun ObtenerUsuario(user: String) {
        this.usuario = user
    }

    fun ObtenerPassword(pass: String) {
        this.password = pass
    }


    fun showpassword() {


        banderapassword = !banderapassword
    }



    fun comprobarcontraseña(pass:String, context: Context): Boolean{
        var correcto by mutableStateOf(false)

        if(pass.length<6){
            showAlertDialog(context,"Alerta","La contraseña tiene que tener más de 6 caracteres")
            correcto=false

        }else{
            showAlertDialog(context,"Alerta","Contraseña guardada correctamente")
            correcto= true
        }
      return  correcto

    }

    fun showAlertDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null) // Botón "OK" sin ninguna acción
        val dialog = builder.create()
        dialog.show()
    }




    fun Guardarusuario(usuario: Usuario){
        val file = File(context.filesDir, "usuarios.txt")
        file.appendText("${usuario.usuario}, ${usuario.contraseña}")

        println(  usuario.toString())
        println("Usuario guardado: ${usuario.usuario}, Contraseña: ${usuario.contraseña}")
        // Mostrar el diálogo después de guardar el usuario
        showDialog.value = true

    }

    fun ObtenerUsuarios(): List<Usuario> {
        val file = File(context.filesDir, "contactos.txt")
        if (!file.exists()) return emptyList()

        return file.readLines().map {
            val (usuario, password) = it.split(",")
            Usuario(usuario, password)

        }

        println(  Usuario(usuario, password))

    }






}