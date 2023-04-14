package com.example.moneyhistory.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun TimeConverter(
    time: LocalDateTime
): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return time.format(formatter)
}