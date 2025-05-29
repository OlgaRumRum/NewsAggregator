package com.example.newsaggregator.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(inputDate: String): String {
    return try {
        val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH)
        val date: Date = inputFormat.parse(inputDate) ?: return ""
        val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}