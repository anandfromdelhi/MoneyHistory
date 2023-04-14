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
import com.example.moneyhistory.utils.MoneyData
import com.example.moneyhistory.utils.SharedViewModel

@Composable
fun AddEditScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    var id: String by remember {
        mutableStateOf("")
    }

    var amount: String by remember {
        mutableStateOf("")
    }
    var description: String by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column() {
        //id
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text(text = "ID") }
        )

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
                val moneyData = MoneyData(id, amount, description)
                sharedViewModel.AddData(moneyData, context)
            }
        ) {
            Text(text = "Save")
        }
    }
}