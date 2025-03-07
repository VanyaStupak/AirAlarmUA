package com.example.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateTime(dateTime: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern(Strings.DATE_PATTERN, Locale.getDefault())
        .withZone(ZoneId.of(Strings.UTS))

    val zonedDateTime = ZonedDateTime.parse(dateTime, inputFormatter)
        .withZoneSameInstant(ZoneId.systemDefault())

    val now = ZonedDateTime.now(ZoneId.systemDefault())
    val todayStart = now.toLocalDate().atStartOfDay(ZoneId.systemDefault())

    val timeFormatter = DateTimeFormatter.ofPattern(Strings.HH_MM, Locale.getDefault())
    val dateFormatter = DateTimeFormatter.ofPattern(Strings.DD_MM, Locale.getDefault())

    return if (zonedDateTime.toLocalDate() == todayStart.toLocalDate()) {
        "${Strings.TODAY_AT} ${zonedDateTime.format(timeFormatter)}"
    } else {
        "${zonedDateTime.format(dateFormatter)} о ${zonedDateTime.format(timeFormatter)}"
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun parseDate(dateTime: String): Long {
    val inputFormatter = DateTimeFormatter.ofPattern(Strings.DATE_PATTERN, Locale.getDefault())
    val localDateTime = LocalDateTime.parse(dateTime, inputFormatter)
    return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

@RequiresApi(Build.VERSION_CODES.O)
fun getElapsedTime(timeString: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern(Strings.DATE_PATTERN, Locale.getDefault())
    val originalTime = LocalDateTime.parse(timeString, inputFormatter)

    val adjustedTime = if (originalTime.second >= 30) {
        originalTime.plusMinutes(1).withSecond(0)
    } else {
        originalTime.withSecond(0)
    }

    val zonedDateTime = adjustedTime
        .atZone(ZoneId.of(Strings.UTS))
        .withZoneSameInstant(ZoneId.systemDefault())

    val now = ZonedDateTime.now()
    val duration = Duration.between(zonedDateTime, now)

    val days = duration.toDays()
    val hours = (duration.toHours() % 24).toInt()
    val minutes = (duration.toMinutes() % 60).toInt()

    return buildString {
        if (days > 0) append("${days}д. ")
        if (hours > 0 || days > 0) {
            append("${hours}год. ")
            if (minutes > 0) append("${minutes}хв.")
        } else if (minutes > 0) {
            append("${minutes}хв.")
        } else {
            append("<1хв")
        }
    }.trim()
}

object Strings {
    const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val TODAY_AT = "Сьогодні о"
    const val HH_MM = "HH:mm"
    const val DD_MM = "dd.MM"
    const val UTS = "UTC"
}