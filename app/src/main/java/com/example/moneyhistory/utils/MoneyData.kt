package com.example.moneyhistory.utils

import java.time.LocalDateTime

data class MoneyData(
    val amount: String = "",
    val description: String = "",
    val time: String = TimeConverter(LocalDateTime.now())
)
