package com.example.examen_segunda_ev.ui.theme.Screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class viewModel :ViewModel() {

    var numCont : String by mutableStateOf("")
    var numContInt by mutableStateOf(0)
    var visible by  mutableStateOf(false)
    val onConvert = {
        numContInt = numCont.toInt()
        visible = !visible
    }

    val OnchangeVis= {
        visible= !visible
    }

   fun suma(){
       numContInt++
   }

    fun resta(){
        numContInt--
    }

   fun onContChange(numero: String){numCont = numero
   }





}

