package com.example.gestion_login.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestion_login.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(viewmodel : viewModelUsuario) {
    val context = LocalContext.current
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center,){


        Row(  modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Image(painter = painterResource(id= R.drawable.login), contentDescription ="Login", alignment = Alignment.Center, )
        }





        OutlinedTextField(
            value =user ,
            onValueChange = { user = it },
            label = { Text("User") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value =password ,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )


        Button(onClick = {


            val contraseñacorrecta =     viewmodel.comprobarpassword(user, password)
            if(contraseñacorrecta== true){
                val toast = Toast.makeText(context, "Contraseña correcta", Toast.LENGTH_SHORT)
                toast.show()

            }else{
                val toast =   Toast.makeText(context, "Contraseña Incorrecta, vuelva a intentarlo", Toast.LENGTH_SHORT)
                user = ""
                password = ""
                val contador = viewmodel.contadorintentos(context)
                if(contador== 0){

                }else {
                    val toast2 =
                        Toast.makeText(context, "Tiene ${contador} Intentos", Toast.LENGTH_SHORT)
                    toast.show()
                    toast2.show()
                }



            }

        }, modifier= Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = "Acceso")
        }
    }

}
@Preview
@Composable
fun preview(){


    val viewModelUsuario =  viewModelUsuario()

    Login(viewModelUsuario)




}
