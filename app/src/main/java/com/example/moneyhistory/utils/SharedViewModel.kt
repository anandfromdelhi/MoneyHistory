package com.example.moneyhistory.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {



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

    fun AddDataSerially(
        moneyData: MoneyData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{
        val db = Firebase.firestore
        db.runTransaction { transaction ->
            val counterRef = db.collection("counter").document("serialNumber")
            val counterSnapshot =  transaction.get(counterRef)
            val nextSerialNumber = counterSnapshot.getLong("nextSerialNumber") ?: 0
            transaction.set(counterRef, mapOf("nextSerialNumber" to nextSerialNumber + 1))
            transaction.set(db.collection("money").document(nextSerialNumber.toString()),moneyData )
        }.addOnSuccessListener { alert(context, "new data") }
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