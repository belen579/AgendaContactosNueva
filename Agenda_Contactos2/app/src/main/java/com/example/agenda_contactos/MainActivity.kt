package com.example.agenda_contactos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.agenda_contactos.app.previewcontacto
import com.example.agenda_contactos.data.viewModelcontacto
import com.example.agenda_contactos.ui.theme.Agenda_ContactosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Agenda_ContactosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    var view = viewModelcontacto(context)
                //   Appcontactos(viewModel = view)

                    previewcontacto()
                }
            }
        }
    }
}

