package com.example.agenda_contactos.ui.persistance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ContactoViewModel(private val repository: ContactoRepository) : ViewModel() {
  val contactos = MutableLiveData<List<Contacto>>()


    init {
        cargarContactos()
    }

    private fun cargarContactos() {
        contactos.value = repository.obtenerContacto()
    }

    fun agregarContacto(contacto: Contacto) {
        repository.agregarContacto(contacto)
        cargarContactos()
    }

    fun eliminarContacto(contacto: Contacto) {
        repository.eliminarContacto(contacto)
        cargarContactos()
    }
}





