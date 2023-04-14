package com.example.moneyhistory.naviagtion


sealed class Screens(val route:String) {
    object MainScreen:Screens(route ="main_screen")
    object AddEditScreen:Screens(route = "add_edit_screen")
}