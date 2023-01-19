package com.udacity.shoestore.domain

import androidx.core.text.isDigitsOnly
import androidx.databinding.InverseMethod

object ShoeSizeConverterUseCase {
    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(
        value: Double
    ): String {
        return when (value) {
            0.0 -> ""
            else -> value.toString()
        }
    }

    @JvmStatic
    fun stringToDouble(
        value: String
    ): Double {
        return when {
            (value.isEmpty()) -> 0.0
            else -> value.toDouble()
        }
    }
}