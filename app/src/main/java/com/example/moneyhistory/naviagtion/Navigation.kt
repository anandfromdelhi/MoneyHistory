package com.example.moneyhistory.naviagtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moneyhistory.Screens.AddEditScreen
import com.example.moneyhistory.Screens.MainScreen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen()
        }
        composable(route = Screens.AddEditScreen.route) {
            AddEditScreen()
        }
    }
}