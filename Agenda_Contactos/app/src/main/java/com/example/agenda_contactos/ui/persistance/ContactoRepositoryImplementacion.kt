package com.example.agenda_contactos.ui.persistance

import android.content.Context
import java.io.File

class ContactoRepositoryImplementacion(private val context: Context): ContactoRepository {



    private val filename= "contactos.txt"
    override fun agregarContacto(contacto: Contacto) {
       val file= File(context.filesDir, filename)
        file.appendText("$contacto.nombre,${contacto.numero}\n")
    }

    override fun eliminarContacto(contacto: Contacto) {
        val file= File(context.filesDir, filename)
        val contactos = obtenerContacto().toMutableList()
        contactos.remove(contacto)
        file.writeText("")
        contactos.forEach{agregarContacto(it)}

    }

    override fun obtenerContacto(): List<Contacto> {
        val file= File(context.filesDir, filename)
        if(!file.exists())return emptyList()

        return  file.readLines().map{
            val(nombre, telefono)= it.split(",")
            Contacto(nombre, telefono)
        }
    }
}