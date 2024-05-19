package com.example.examen_segunda_ev.ui.theme.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable()
fun variables(){


    var numCont : String by remember { mutableStateOf("") }
    var numContInt by remember { mutableStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    val onConvert = {
        numContInt = numCont.toInt()
        visible = !visible
    }
    primeraactivity(
        numString=numCont, onContChange= {numCont = it}, numContInt = numContInt,
        onIncrement = {numContInt++}, onDecrement = { numContInt-- },
        onConvert = onConvert, visible = visible, onChangeVis = {visible = !visible}
    )
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun primeraactivity(numString: String, onContChange:(String)->Unit, numContInt : Int,
                    onIncrement : () ->Unit, onDecrement: () -> Unit, onConvert: ()->Unit, visible : Boolean,
                    onChangeVis : () -> Unit) {

    val elementos: List<String> = List(numContInt) { "Item ${it}" }




    Scaffold(modifier = Modifier.background(Color.Blue),
        topBar = {
           TopAppBar(
                   colors = TopAppBarDefaults.topAppBarColors(
                       containerColor = MaterialTheme.colorScheme.surfaceTint,
                       titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                   ), title = { Text(text = ("Examen"))
              },
                actions = {
                    if (visible) {
                        IconButton(onClick = onChangeVis) {
                            Icon(Icons.Default.Refresh, contentDescription = null, tint= Color.Magenta)

                        }
                        IconButton(onClick = onIncrement) {
                            Icon(Icons.Default.Add, contentDescription = null, tint= Color.Magenta)
                        }
                        IconButton(onClick = onDecrement) {
                            Icon(Icons.Default.Delete, contentDescription = null, tint= Color.Magenta)
                        }
                    }

                })

        }
    ) {


        Column(


            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        ) {
            if (!visible) {
                TextField(
                    value = numString,
                    onValueChange = onContChange,
                    label = { Text(text = "Numero de contadores") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Button(onClick = onConvert) {
                    Text(text = "Acceder")
                }
                Spacer(modifier = Modifier.padding(15.dp))
            }
            if (visible) {

                LazyColumn {
                    items(elementos) { el ->
                        LazyColumnelementos(elemento = el)
                    }
                }

            }
        }
    }
}












@Composable
fun LazyColumnelementos(elemento:String) {
    
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Column (verticalArrangement = Arrangement.Center){
        
        Row(  modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically){
            
            Text(text = "Item "+ elemento)
            
            IconButton(onClick = { count++ }) {
                Icon(imageVector = Icons.Default.Add , contentDescription =null )
                
            }
            IconButton(onClick = { count-- }) {
                Icon(imageVector = Icons.Default.Delete , contentDescription =null )

            }
            Text(text = "Item  $count")
            
        }



    }



}


