package me.ashutoshkk.stonks.common

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import me.ashutoshkk.stonks.domain.model.FilterType
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

fun convertToType(str: String): FilterType {
    FilterType.entries.forEach {
        if (str == it.name) return it
    }
    return FilterType.None
}

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
        else -> false
    }
}