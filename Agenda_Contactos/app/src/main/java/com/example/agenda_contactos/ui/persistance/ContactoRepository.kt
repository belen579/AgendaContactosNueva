package com.example.agenda_contactos.ui.persistance

interface ContactoRepository {


    fun agregarContacto(contacto: Contacto)
    fun eliminarContacto(contacto: Contacto)
    fun obtenerContacto():List<Contacto>
}