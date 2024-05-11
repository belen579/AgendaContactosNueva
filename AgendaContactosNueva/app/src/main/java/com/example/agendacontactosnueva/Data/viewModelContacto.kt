package com.example.agendacontactos_objetos.Data

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.agendacontactosnueva.R

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream





class viewModelContacto(private val context: Context): ViewModel() {

    var list = mutableListOf<Contacto>()
    var nextid: Int = 1

    var FILE = File(context.filesDir, "nuevo.dat")

    var nombre by mutableStateOf("")
    var telefono by mutableStateOf("")
    var email by mutableStateOf("")



    var contactorecibido by mutableStateOf(Contacto("","","",0,0))
    private set
  /*  fun obtenerNombreContactoRecibido(): String {
        return contactorecibido.nombre
    }

    // Método para obtener el email del contacto recibido
    fun obtenerEmailContactoRecibido(): String {
        return contactorecibido.email
    }

    // Método para obtener el teléfono del contacto recibido
    fun obtenerTelefonoContactoRecibido(): String {
        return contactorecibido.telefono
    }
*/

    init {


        if (!FILE.exists()) {
            list = mutableListOf(
                Contacto("Ana", "655889963", "ana@gmail.com", R.drawable.ana, 1),
                Contacto("Abuelo", "655889967", "abuelo@gmail.com", R.drawable.abuelo, 2),
                Contacto("Abuela", "655888963", "abuela@gmail.com", R.drawable.abuela, 3),
                Contacto("Tio", "655889973", "tio@gmail.com", R.drawable.tio, 4),
                Contacto("Juan", "655886663", "juan@gmail.com", R.drawable.chico1, 5),
                Contacto("Camilo", "677889963", "camilo@gmail.com", R.drawable.camilo, 6),
                Contacto("Angel", "663389967", "angel@gmail.com", R.drawable.angel, 7),
                Contacto("Natalia", "677888963", "natalia@gmail.com", R.drawable.natalia, 8),
                Contacto("Ivan", "666559973", "ivan@gmail.com", R.drawable.ivan, 9),
                Contacto("Raquel", "612346663", "raquel@gmail.com", R.drawable.raquel, 10)
            )
            guardarContactosEnArchivo()


        }else{
            list = cargarContactos()
        }
    }

    private fun guardarContactosEnArchivo() {


        try {
            val objectOutputStream = ObjectOutputStream(FileOutputStream(FILE))
            list.forEach {contacto->
                objectOutputStream.writeObject(contacto)
            }
            objectOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()

        }

    }
    fun cargarContactos(): MutableList<Contacto> {


        if (FILE.exists()) {
            try {
                list.clear()
                val objectInputStream = ObjectInputStream(FileInputStream(FILE))
                var contacto: Contacto? = null
                while (true) {
                    contacto = objectInputStream.readObject() as? Contacto ?: break
                    list.add(contacto)
                    if (contacto.id >= nextid) {
                        nextid = contacto.id + 1
                    }
                }
                objectInputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }
        return list
    }


    @SuppressLint("SuspiciousIndentation")
    fun guardarcontactoarchivo(contacto: Contacto) {

        list.add(contacto)



        try {
            val objectOutputStream = ObjectOutputStream(FileOutputStream(FILE))
            list.sortBy {it.nombre  }
            list.forEach {contacto->
                objectOutputStream.writeObject(contacto)
            }
            objectOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()

        }


    // Importante en objetos no podemos poner append a true para que escriba al final el ObjectOutputStream
    // no es compatible con esta forma de escritura en archivos.
    // Hay que en un archivo abierto en modo de adición simplemente escribirá la nueva información al final del archivo,
    // pero no será capaz de manejar correctamente el flujo de objetos serializados.
    // //Para resolver esto, debes mantener el archivo cerrado y abrirlo en modo de escritura normal cada vez que necesites escribir un nuevo objeto.


    }

    fun borrar(contactoenviado: Contacto) {
        println("lista antes de borrar")
        println(list)
       // println(contactoenviado.toString())
        list.remove(contactoenviado)
        println("lista despues de borrar")
        println(list)
        if(FILE.delete()){
            println("Archivo borrado")
        }else{
            println("Archivo NO BORRADO")
        }

        guardarContactosEnArchivo()


    }

    @SuppressLint("SuspiciousIndentation")
    fun modificar(contactoenviado: Contacto) {


        contactorecibido= contactoenviado


        //borrar(contactoenviado)





        println(contactorecibido.nombre)








    }



   /*     // Crea una lista actualizada excluyendo el contacto que deseas eliminar
        for (c in list) {
            if (c != contacto) {
                contactosActualizados.add(c)
            }
        }

        // Reescribe el archivo con la lista actualizada
        guardarContactosEnArchivo(contactosActualizados)

        // Actualiza la lista en memoria con la lista actualizada
        list = contactosActualizados
        */


    /*    val contactosActualizados = list.filter { it != contacto }.toMutableList() // Filtra los contactos, excluyendo el contacto a borrar
        guardarContactosEnArchivo(contactosActualizados) // Guarda los contactos actualizados en el archivo
        list = contactosActualizados // Actualiza la lista en memoria

*/
    }




