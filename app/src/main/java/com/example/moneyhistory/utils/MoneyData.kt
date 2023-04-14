package com.example.moneyhistory.utils

import java.time.LocalDateTime

data class MoneyData(
    val amount:Double = 0.0,
    val description:String = "",
    val time: String = TimeConverter(LocalDateTime.now())
)
