package com.example.gestion_login.Navigation

sealed class Screens(val route: String) {


    object inicio: Screens("Login")
    object principal : Screens ("Principal")





}