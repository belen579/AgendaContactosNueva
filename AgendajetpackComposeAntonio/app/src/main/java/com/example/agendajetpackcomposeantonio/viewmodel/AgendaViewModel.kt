package com.antonio.agendajetpackcompose.ui.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import com.antonio.agendajetpackcompose.R
import com.antonio.agendajetpackcompose.ui.model.Contactos
import com.antonio.agendajetpackcompose.ui.model.ContactosFinales
import java.io.ByteArrayOutputStream
import java.io.EOFException
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class AgendaViewModel {


    var lista = mutableListOf<Contactos>(
        Contactos(
            0,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            R.drawable.jugadordesconocido
        ),
        Contactos(
            1,
            "Marc-André",
            "Ter Stegen",
            "Passeig de Gràcia 34 2ºB",
            "08007",
            "Barcelona",
            "Barcelona",
            "932145258",
            "678521465",
            "marcstegen@gmail.com",
            "30 Abril",
            "Portero sobrio y con grandes reflejos, destaca por el control aéreo y el dominio del juego de pies.",
            R.drawable.marc_andre
        ),
        Contactos(
            2,
            "João",
            "Cancelo",
            "La rambla 324 7º",
            "08002",
            "Barcelona",
            "Barcelona",
            "934545247",
            "677856465",
            "joaocancelo@gmail.com",
            "27 Mayo",
            "Dueño y señor del lateral derecho con una exuberancia física y una habilidad envidiable ha pasado por grandes clubs del nivel europeo.",
            R.drawable.joao_cancelo
        ),
        Contactos(
            3,
            "Alejandro",
            "Balde",
            "Avinguda Diagonal 214 Atico A",
            "08005",
            "Barcelona",
            "Barcelona",
            "934545245",
            "677456478",
            "alejandrobaldeo@gmail.com",
            "18 Octubre",
            "Dotado física y técnicamente, Alejandro Balde es un lateral explosivo y rápido con capacidad para incorporarse al ataque.",
            R.drawable.alejandro_balde
        ),
        Contactos(
            4,
            "Ronald",
            "Araujo",
            "Avinguda Diagonal 214 Atico B",
            "08005",
            "Barcelona",
            "Barcelona",
            "934543457",
            "677768903",
            "ronaldaraujo@gmail.com",
            "7 Marzo",
            "Central uruguayo con proyección, con buena salida de balón y dominio del juego aéreo.",
            R.drawable.ronald_araujo
        ),
        Contactos(
            5,
            "Iñigo",
            "Martinez",
            "Carrer del Bisbe 146 5ºC",
            "08001 ",
            "Barcelona",
            "Barcelona",
            "934544744",
            "677786785",
            "iñigomartinez@gmail.com",
            "17 Mayo",
            "Después de una década ininterrumpida en la élite del fútbol español, Iñigo Martinez ha demostrado ser un central con una gran salida de balón.",
            R.drawable.inigo_martinez
        ),
        Contactos(
            6,
            "Pablo",
            "Paez Gavira",
            "Passeig del Born 6 4ºA",
            "08001 ",
            "Barcelona",
            "Barcelona",
            "934564745",
            "677787852",
            "gavibarca@gmail.com",
            "5 Agosto",
            "Centrocampista con una gran calidad técnica y visión de juego, con carácter e intensidad, que le convierten en un jugador importante en el medio del campo.",
            R.drawable.gavi
        ),
        Contactos(
            7,
            "Ferran",
            "Torres",
            "Carrer Petritxol 56",
            "08012 ",
            "Barcelona",
            "Barcelona",
            "934523445",
            "677787458",
            "ferrantorres@gmail.com",
            "29 Febrero",
            "Puede ocupar cualquier posición ofensiva, exhibiendo velocidad, verticalidad, calidad e inteligencia.",
            R.drawable.ferran_torres
        ),
        Contactos(
            8,
            "Pedro",
            "Gonzalez Lopez",
            "Carrer Petritxol 76",
            "08012 ",
            "Barcelona",
            "Barcelona",
            "934578451",
            "677782365",
            "pedribarcelona@gmail.com",
            "25 Noviembre",
            "Gusto por el fútbol combinativo, el joven futbolista tiene una excelente conducción del balon y sus milimétricos pases rompen líneas defensivas.",
            R.drawable.pedri
        ),

        )
        private set

    var contacto by mutableStateOf(Contactos(0, "", "", "", "", "", "", "", "", "", "", "", 0))
        private set

    var contactofinal by mutableStateOf(ContactosFinales(0, "", "", "", "", "", "", "", "", "", "", "", null))
        private set

    var contactofinalAux by mutableStateOf(ContactosFinales(0, "", "", "", "", "", "", "", "", "", "", "", null))
        private set

    var id by mutableStateOf(0)
        private set
    var nombre by mutableStateOf("")
        private set
    var apellidos by mutableStateOf("")
        private set

    var direccion by mutableStateOf("")
        private set

    var codigoPostal by mutableStateOf("")
        private set

    var ciudad by mutableStateOf("")
        private set

    var provincia by mutableStateOf("")
        private set

    var telefonoFijo by mutableStateOf("")
        private set

    var telefonoMovil by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var cumpleaños by mutableStateOf("")
        private set

    var observaciones by mutableStateOf("")
        private set

    var foto by mutableStateOf(0)
        private set


    var listaContactosLeidos = mutableListOf<ContactosFinales>()
        private set

    val nombreArchivo="contactos9.dat"





//    var listaContactosParaUtilizarApp = mutableListOf<Contactos>()
//        private set
//
//    fun getListaContactosParaUtilizarApp(): MutableList<Contactos> {
//        return listaContactosParaUtilizarApp
//    }

    fun getContactoFinal(contactoFinal:ContactosFinales){
        this.contactofinal=contactoFinal
    }

    fun getContactoFinalAux(contactoFinalAux:ContactosFinales){
        this.contactofinalAux=contactoFinalAux
    }

    fun getFoto(foto:Int){
        this.foto=foto
    }

    fun getId(id: Int) {
        this.id=id
    }
    fun getNombre(nombre: String) {
        this.nombre=nombre
    }

    fun getApellidos(apellidos: String) {
        this.apellidos=apellidos
    }

    fun getDireccion(direccion: String) {
        this.direccion=direccion
    }

    fun getCodigoPostal(codigoPostal: String) {
        this.codigoPostal=codigoPostal
    }

    fun getCiudad(ciudad: String) {
        this.ciudad=ciudad
    }

    fun getProvincia(provincia: String) {
        this.provincia=provincia
    }

    fun getTelefonoFijo(telefonoFijo: String) {
        this.telefonoFijo=telefonoFijo
    }

    fun getTelefonoMovil(telefonoMovil: String) {
        this.telefonoMovil=telefonoMovil
    }

    fun getEmail(email: String) {
        this.email=email
    }

    fun getCumpleanhos(cumpleaños: String) {
        this.cumpleaños=cumpleaños
    }

    fun getObservaciones(observaciones: String) {
        this.observaciones=observaciones
    }

//    fun getFoto(foto: ByteArray?) {
//        this.foto= foto as Nothing?
//    }

    fun guardarListaEnFichero(context: Context) {
        var archivo = File(context.filesDir, nombreArchivo)
        val rutaArchivo=archivo.absolutePath
        println("la ruta del archivo es: $rutaArchivo")
        if(!archivo.exists()){
            val objectOutputStream = ObjectOutputStream(FileOutputStream(archivo))
            var contador = 0
            lista.forEach { item ->
                contador++

                var fotoByteArrays: ByteArray
                fotoByteArrays = obtenerBytesDeDrawable(context, item.foto)
                var contactoFinal = ContactosFinales(
                    item.id,
                    item.nombre,
                    item.apellidos,
                    item.Direccion,
                    item.codigoPostal,
                    item.ciudad,
                    item.provincia,
                    item.telefonoFijo,
                    item.telefonoMovil,
                    item.email,
                    item.cumpleaños,
                    item.observaciones,
                    fotoByteArrays
                )


                // Serializar objeto
                serializarObjeto(contactoFinal, objectOutputStream)



            }
            objectOutputStream.close()
        }

    }
    fun guardarContactoEnFichero(context: Context,contactoFinal: ContactosFinales){
        try{
            var archivo = File(context.filesDir, nombreArchivo)
            //val objectOutputStream = ObjectOutputStream(FileOutputStream(archivo,true))
            val objectOutputStream = object : ObjectOutputStream(FileOutputStream(archivo,true)) {
                override fun writeStreamHeader() {}  //para no sobreescribir la cabecera del archivo
            }
            serializarObjeto(contactoFinal, objectOutputStream)
            objectOutputStream.close()
            println("Objeto agregado correctamente al archivo.")
        } catch (ex: IOException) {
            println("Error al escribir el objeto en el archivo: ${ex.message}")
        }

    }

    fun serializarObjeto(objeto: ContactosFinales, objectOutputStream: ObjectOutputStream) {
        objectOutputStream.writeObject(objeto)
    }

    fun leerContactosArchivo(context: Context): MutableList<ContactosFinales> {
        var archivo = File(context.filesDir, nombreArchivo)
        listaContactosLeidos.clear()
        listaContactosLeidos = deserializarObjeto(archivo)
        listaContactosLeidos.sortBy { it.apellidos }
        return listaContactosLeidos
    }

    fun borrarContacto(context: Context, contactoFinal: ContactosFinales){
        println(contactoFinal)
        var id=contactoFinal.id
        //no se puede borrar con un objeto de una mutablelist mientras se esta recorriendo, por eso se utilizo el removeIf
        //tambien se ha tenido que utilizar la variable id ya que si se comparaba en el removeIf los objetos enteros no funcionaba OK el borrado del objeto
        listaContactosLeidos.removeIf{it.id==id}
        escribirFichero(context)




    }

    fun escribirFichero(context: Context){
        var archivo = File(context.filesDir, nombreArchivo)
        val objectOutputStream = ObjectOutputStream(FileOutputStream(archivo))
        listaContactosLeidos.forEach(){item->
            serializarObjeto(item, objectOutputStream)
        }
        objectOutputStream.close()
    }





    fun deserializarObjeto(archivo: File): MutableList<ContactosFinales> {
        try {
            val objectInputStream = ObjectInputStream(FileInputStream(archivo))

            while (true) {
                try {
                    val contacto = objectInputStream.readObject()
                    if (contacto is ContactosFinales) {
                        listaContactosLeidos.add(contacto)
                       // println(contacto.nombre)
                    } else {
                        break;
                    }

                } catch (ex: EOFException) {
                    break
                }
            }

            objectInputStream.close()
        } catch (ex: IOException) {
            println("Error al leer el archivo: ${ex.message}")
        } catch (ex: ClassNotFoundException) {
            println("Clase no encontrada: ${ex.message}")
        }

        return listaContactosLeidos
    }


    fun obtenerBytesDeDrawable(context: Context, @DrawableRes foto: Int): ByteArray {
        val drawable = ContextCompat.getDrawable(context, foto)
        val bitmap = drawable?.toBitmap() // Convertir el drawable en un bitmap
        val stream = ByteArrayOutputStream()
        bitmap?.compress(
            Bitmap.CompressFormat.PNG,
            100,
            stream
        ) // Comprimir el bitmap a un flujo de salida
        return stream.toByteArray() // Convertir el flujo de salida en un array de bytes
    }

    fun ByteArrayToImage(byteArray: ByteArray):ImageBitmap {
        val bitmap = byteArray.decodeBitmap()
        val imageBitmap = bitmap.asImageBitmap()
        return imageBitmap
    }
    // Helper function to decode ByteArray into Bitmap
    private fun ByteArray.decodeBitmap(): android.graphics.Bitmap {
        return android.graphics.BitmapFactory.decodeByteArray(this, 0, size)
    }






    fun setaContacto(contacto: Contactos) {//se le llama setaContacto por ya existir otro metodo setContacto
        this.contacto = contacto
    }
    fun setaContactoFinales(contactoFinal: ContactosFinales) {//se le llama setaContacto por ya existir otro metodo setContacto
        this.contactofinal = contactoFinal
    }


    fun getListaContactos(): MutableList<Contactos> {
        return lista
    }

//    fun getListaContactosLeidos(): MutableList<ContactosFinales>{
//        return listaContactosLeidos
//    }

    fun IrInicio() {
        contactofinal = listaContactosLeidos[1]
    }

    fun IrFinal() {
        contactofinal = listaContactosLeidos.last()
    }

    fun IrDelante() {
        var indice = 0
        listaContactosLeidos.forEach { item ->
            if (item == contactofinal) {
                indice = listaContactosLeidos.indexOf(item) + 1

            }

        }
        if (indice < listaContactosLeidos.size) {
            contactofinal = listaContactosLeidos[indice]
        } else {
            contactofinal = listaContactosLeidos[indice - 1]
        }
    }

    fun IrAtras() {
        var indice = 0
        listaContactosLeidos.forEach { item ->
            if (item == contactofinal) {
                indice = listaContactosLeidos.indexOf(item) - 1

            }

        }
        if (indice >= 0) {
            contactofinal = listaContactosLeidos[indice]
        } else {
            contactofinal = listaContactosLeidos[indice + 1]
        }
    }

    fun CalcularId():Int{
        return listaContactosLeidos.maxOf { it.id }

    }

    fun limpiarAtributos() {
        getNombre("")
        getApellidos("")
        getTelefonoFijo("")
        getTelefonoMovil("")
        getDireccion("")
        getCodigoPostal("")
        getCiudad("")
        getProvincia("")
        getEmail("")
        getCumpleanhos("")
        getObservaciones("")
    }

    fun cargarAtributosContacto() {

        getNombre(contactofinal.nombre)
        getApellidos(contactofinal.apellidos)
        getTelefonoFijo(contactofinal.telefonoFijo)
        getTelefonoMovil(contactofinal.telefonoMovil)
        getDireccion(contactofinal.Direccion)
        getCodigoPostal(contactofinal.codigoPostal)
        getCiudad(contactofinal.ciudad)
        getProvincia(contactofinal.provincia)
        getEmail(contactofinal.email)
        getCumpleanhos(contactofinal.cumpleaños)
        getObservaciones(contactofinal.observaciones)
    }

    fun editarContactoEnFichero(context: Context, contactofinal: ContactosFinales, contactofinalAux: ContactosFinales) {
        try{
            var archivo = File(context.filesDir, nombreArchivo)
            borrarContacto(context,contactofinal)

            val objectOutputStream = object : ObjectOutputStream(FileOutputStream(archivo,true)) {
                override fun writeStreamHeader() {}  //para no sobreescribir la cabecera del archivo
            }
            serializarObjeto(contactofinalAux, objectOutputStream)
            objectOutputStream.close()
            println("Objeto agregado correctamente al archivo.")
        } catch (ex: IOException) {
            println("Error al escribir el objeto en el archivo: ${ex.message}")
        }


    }


}