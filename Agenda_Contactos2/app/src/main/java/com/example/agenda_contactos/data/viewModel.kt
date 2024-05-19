package com.example.agenda_contactos.data

import android.app.AlertDialog
import android.content.Context
import android.widget.EditText
import android.widget.Toast

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.agenda_contactos.R
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.InputStream
import java.io.OutputStreamWriter

class viewModelcontacto(val context: Context):ViewModel( ) {


    var nombre: String by mutableStateOf("")
    var telefono: String by mutableStateOf("")
    var contactos: List<Contacto> by mutableStateOf(emptyList())

    init {
        // Al inicializar el ViewModel, cargamos los contactos
        cargarContactos()
    }

    fun cargarContactos() {
        contactos = obtenerContactos()
    }


    fun agregarContacto(contacto: Contacto) {

        val file = File(context.filesDir, "contactos.txt")
        file.appendText("${contacto.nombre},${contacto.telefono}\n")
        cargarContactos()
    }


    fun obtenerContactos(): List<Contacto> {
        val file = File(context.filesDir, "contactos.txt")
        if (!file.exists()) return emptyList()

        return file.readLines().map {
            val (nombre, telefono) = it.split(",")
            Contacto(nombre, telefono)
        }
    }

    fun eliminarContacto(contacto: Contacto) {
        val file = File(context.filesDir, "contactos.txt")
        val contactosActualizados = contactos.toMutableList()
        contactosActualizados.remove(contacto)
        file.writeText("") // Limpiar el archivo
        contactosActualizados.forEach { agregarContacto(it) }

    }


    fun modificarContacto(contactoModificado: Contacto) {
        val file = File(context.filesDir, "contactos.txt")
        val contactos = obtenerContactos()
        val contactoAModificar = contactos.find { it.nombre == contactoModificado.nombre }




        if (contactoAModificar != null) {

            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("Modificar Telefono")

            val inputTelefono = EditText(context)
            inputTelefono.setText(contactoAModificar.telefono)
            alertDialogBuilder.setView(inputTelefono)



            alertDialogBuilder.setPositiveButton("Aceptar") { dialog, which ->


                val nuevoTelefono = inputTelefono.text.toString()


                // Realizar la actualización del contacto con los nuevos valores
                val contactoActualizado = Contacto(contactoAModificar.nombre, nuevoTelefono)

                actualizarContacto(contactoActualizado)
                eliminarContacto(contactoAModificar)
            }



            alertDialogBuilder.setNegativeButton("Cancelar") { dialog, which ->

            }

            alertDialogBuilder.setNeutralButton("Cambiar nombre") { dialog, which ->

                nuevodialogo(contactoModificado)
            }


            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }


    }

    fun actualizarContacto(contactoActualizado: Contacto) {

        agregarContacto(contactoActualizado)

    }


    fun nuevodialogo(contactoModificado: Contacto) {

        val contactos = obtenerContactos()
        val contactoAModificar = contactos.find { it.nombre == contactoModificado.nombre }




        if (contactoAModificar != null) {

            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("Modificar Nombre")


            val inputNombre = EditText(context)
            inputNombre.setText(contactoAModificar.nombre)
            alertDialogBuilder.setView(inputNombre)






            alertDialogBuilder.setPositiveButton("Aceptar") { dialog, which ->

                val nuevoNombre = inputNombre.text.toString()



                // Realizar la actualización del contacto con los nuevos valores
                val contactoActualizado = Contacto(nuevoNombre, contactoModificado.telefono)

                actualizarContacto(contactoActualizado)
                eliminarContacto(contactoAModificar)
            }



            alertDialogBuilder.setNegativeButton("Cancelar") { dialog, which ->

            }


            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }


    }
}














