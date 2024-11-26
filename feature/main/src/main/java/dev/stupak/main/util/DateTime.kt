package dev.stupak.main.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

fun formatDateTime(dateTime: String): String {
    val inputFormat = SimpleDateFormat(Const.DATE_PATTERN, Locale.getDefault()).apply {
        timeZone = TimeZone.getDefault()
    }
    val date = inputFormat.parse(dateTime) ?: return ""

    val calendar = Calendar.getInstance()
    calendar.time = date

    val today = Calendar.getInstance()

    val timeFormat = SimpleDateFormat(Const.HH_MM, Locale.getDefault())
    val dateFormat = SimpleDateFormat(Const.DD_MM, Locale.getDefault())

    return if (
        calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
        calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
    ) {
        "${Const.TODAY_AT} ${timeFormat.format(date)}"
    } else {
        "${dateFormat.format(date)} о ${timeFormat.format(date)}"
    }
}

fun parseDate(dateTime: String): Long {
    val inputFormat = SimpleDateFormat(Const.DATE_PATTERN, Locale.getDefault()).apply {
        timeZone = TimeZone.getDefault()
    }
    return inputFormat.parse(dateTime)?.time ?: 0L
}

fun getElapsedTime(timeString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    dateFormat.timeZone = TimeZone.getDefault()
    val date = dateFormat.parse(timeString) ?: return ""

    // Получаем текущее время и считаем разницу в миллисекундах
    val now = Date()
    val diffInMillis = now.time - date.time

    val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
    val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis) % 60

    return buildString {
        if (days > 0) append("${days}д. ")
        if (hours > 0 || days > 0) append("${hours}год. ")
        append("${minutes}хв.")
    }.trim()
}