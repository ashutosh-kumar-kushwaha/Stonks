package me.ashutoshkk.stonks.common

import android.icu.text.SimpleDateFormat
import me.ashutoshkk.stonks.domain.model.Type
import java.util.Date
import java.util.Locale

fun convertTo12HourFormat(dateTimeString: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("dd MMM h:mm a", Locale.getDefault())
    val date: Date = inputFormatter.parse(dateTimeString)!!
    return outputFormatter.format(date)
}

fun convertToDate(dateTimeString: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("dd MMM", Locale.getDefault())
    val date: Date = inputFormatter.parse(dateTimeString)!!
    return outputFormatter.format(date)
}

fun convertToMonth(dateTimeString: String): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormatter = SimpleDateFormat("MMM yyyy", Locale.getDefault())
    val date: Date = inputFormatter.parse(dateTimeString)!!
    return outputFormatter.format(date)
}

fun convertToType(str: String): Type {
    Type.entries.forEach {
        if (str == it.name) return it
    }
    return Type.Equity
}