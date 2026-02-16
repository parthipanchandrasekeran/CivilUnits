package com.civilunits.canada.data.model

data class UnitCategory(
    val id: String,
    val name: String,
    val iconName: String,
    val units: List<UnitDef>
)

data class UnitDef(
    val id: String,
    val name: String,
    val symbol: String,
    val toBase: (Double) -> Double,
    val fromBase: (Double) -> Double
)

fun convert(value: Double, from: UnitDef, to: UnitDef): Double =
    to.fromBase(from.toBase(value))
