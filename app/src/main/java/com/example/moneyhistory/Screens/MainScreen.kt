package com.example.moneyhistory.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.example.moneyhistory.utils.MoneyData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

@Composable
fun MainScreen(){

    val db = Firebase.firestore
    var list by remember{
        mutableStateOf(listOf<MoneyData>())
    }

    LaunchedEffect(Unit){
        db.collection("money").get().addOnSuccessListener {result ->
            list = result.mapNotNull { it.toObject() }

        }
    }
    
    LazyColumn{
        items(list){it ->
            Column(){
                Text(text = it.amount)
                Text(text = it.description)
            }

        }
    }

}