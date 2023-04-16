package com.example.moneyhistory.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moneyhistory.naviagtion.Screens
import com.example.moneyhistory.utils.MoneyData
import com.example.moneyhistory.utils.SharedViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun AddEditScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {


    var amount: String by remember {
        mutableStateOf("")
    }
    var description: String by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column() {


        //amount
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text(text = "amount") }
        )

        //description
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Description") }
        )
        Button(
            modifier = Modifier.padding(top = 50.dp), onClick = {
                val moneyData = MoneyData(amount, description)

                val db = Firebase.firestore

                sharedViewModel.AddDataSerially(moneyData, context)
            }
        ) {
            Text(text = "Save")
        }
        Button(onClick = { navController.navigate(Screens.MainScreen.route) }) {
            Text(text = "go to main Screen")
        }
    }
}