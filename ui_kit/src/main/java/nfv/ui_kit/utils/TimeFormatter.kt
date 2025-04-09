package nfv.ui_kit.utils

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

fun timeFormatter(instant: Instant): String {
    val zoneId = ZoneId.systemDefault()
    val dateTime = instant.atZone(zoneId)

    val now = ZonedDateTime.now(zoneId)

    return when {
        dateTime.toLocalDate() == now.toLocalDate() -> {
            // Same day → show only time
            dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        }

        dateTime.year == now.year -> {
            // Same year → show day & time without year
            dateTime.format(DateTimeFormatter.ofPattern("d MMM, HH:mm", Locale.getDefault()))
        }

        else -> {
            // Different year → include year
            dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", Locale.getDefault()))
        }
    }
}
