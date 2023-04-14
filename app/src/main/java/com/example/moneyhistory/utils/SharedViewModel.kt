package com.example.moneyhistory.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    fun AddData(
        moneyData: MoneyData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireRef = Firebase.firestore.collection("money").document(moneyData.id)
        try {
            fireRef.set(moneyData).addOnSuccessListener { alert(context, "added successfully") }

        } catch (e: Exception) {
            alert(context, e.message)
        }

    }

    fun ReadData(
        context: Context,
        id: String,
        data: (MoneyData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireRef = Firebase.firestore.collection("money").document(id)
        try {
            fireRef.get().addOnSuccessListener {
                if (it.exists()) {
                    val moneyData = it.toObject<MoneyData>()!!
                    data(moneyData)
                } else {
                    alert(context, "data not found")
                }
            }
        } catch (e: Exception) {
            alert(context, e.message)
        }

    }

    fun DeleteData(
        id: String,
        context: Context,
        navController: NavController
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireRef = Firebase.firestore.collection("money").document(id)

        try {
            fireRef.delete().addOnSuccessListener {
                alert(context,"deleted successfully")
                navController.popBackStack()
            }
        }catch (e:Exception){
            alert(context,e.message)
        }

    }
}