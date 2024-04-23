package ru.adavydova.ui_component.adapter_delegate.utils

import android.icu.number.NumberFormatter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.Period
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.util.Locale

fun Int.priceFormatter() = NumberFormat.getCurrencyInstance(Locale("ru","RU")).format(this)
fun String.getDiffBetweenTwoDate(arrival: String) : String{
    val localDateTimeDeparture = LocalDateTime.parse(this)
    val localDateTimeArrival = LocalDateTime.parse(arrival)

    val diffInMillisec = ChronoUnit.MINUTES.between(localDateTimeDeparture, localDateTimeArrival)
    return "${diffInMillisec / 60}.${diffInMillisec % 60}"

}