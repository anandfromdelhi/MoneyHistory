package com.example.moneyhistory.utils

import android.content.Context
import android.widget.Toast

fun alert(
    context:Context,
    text: String?
){
    return Toast.makeText(context,text,Toast.LENGTH_LONG).show()
}