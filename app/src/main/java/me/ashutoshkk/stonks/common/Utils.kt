package me.ashutoshkk.stonks.common

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertTo12HourFormat(dateTimeString: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("h:mm a", Locale.getDefault())
    val date: Date = inputFormatter.parse(dateTimeString)!!
    return outputFormatter.format(date)
}
