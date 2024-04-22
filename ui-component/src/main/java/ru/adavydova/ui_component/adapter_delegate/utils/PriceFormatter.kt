package ru.adavydova.ui_component.adapter_delegate.utils

import android.icu.number.NumberFormatter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Int.priceFormatter() = NumberFormat.getCurrencyInstance(Locale("ru","RU")).format(this)