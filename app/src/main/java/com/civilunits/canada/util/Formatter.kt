package com.civilunits.canada.util

import com.civilunits.canada.data.model.PrecisionMode
import java.math.RoundingMode
import java.text.DecimalFormat

object Formatter {

    fun formatResult(value: Double, precision: PrecisionMode): String {
        if (value.isNaN()) return "NaN"
        if (value.isInfinite()) return if (value > 0) "\u221E" else "-\u221E"

        return when (precision) {
            PrecisionMode.Auto -> formatAuto(value)
            PrecisionMode.Dec2 -> formatFixed(value, 2)
            PrecisionMode.Dec4 -> formatFixed(value, 4)
            PrecisionMode.Dec6 -> formatFixed(value, 6)
        }
    }

    private fun formatAuto(value: Double): String {
        if (value == 0.0) return "0"

        val abs = kotlin.math.abs(value)

        // Use scientific notation for very large or very small numbers
        if (abs >= 1e10 || (abs < 1e-6 && abs > 0)) {
            val df = DecimalFormat("0.######E0")
            df.roundingMode = RoundingMode.HALF_UP
            return df.format(value)
        }

        // Up to 10 significant digits, strip trailing zeros
        val df = DecimalFormat("#.##########")
        df.roundingMode = RoundingMode.HALF_UP
        return df.format(value)
    }

    private fun formatFixed(value: Double, decimals: Int): String {
        val pattern = "0." + "#".repeat(0) + "0".repeat(decimals)
        val df = DecimalFormat(pattern)
        df.roundingMode = RoundingMode.HALF_UP
        return df.format(value)
    }
}
