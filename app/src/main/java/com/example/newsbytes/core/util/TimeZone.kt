package com.example.newsbytes.core.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
fun String.toISTFormat(): String {
    if (this.isBlank()) return "Unknown Date"

    return try {
        val instant = Instant.parse(this)
        val istZone = ZoneId.of("Asia/Kolkata")
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a").withZone(istZone)

        formatter.format(instant)
    } catch (_: DateTimeParseException) {
        "Recently"
    }
}