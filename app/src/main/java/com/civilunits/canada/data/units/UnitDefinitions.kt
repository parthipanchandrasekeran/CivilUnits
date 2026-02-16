package com.civilunits.canada.data.units

import com.civilunits.canada.data.model.UnitCategory
import com.civilunits.canada.data.model.UnitDef
import kotlin.math.atan
import kotlin.math.tan

object UnitDefinitions {

    val categories: List<UnitCategory> = listOf(
        // ── Length (base: meter) ──
        UnitCategory(
            id = "length",
            name = "Length",
            iconName = "straighten",
            units = listOf(
                UnitDef("mm", "Millimeter", "mm",
                    toBase = { it * 0.001 },
                    fromBase = { it / 0.001 }),
                UnitDef("cm", "Centimeter", "cm",
                    toBase = { it * 0.01 },
                    fromBase = { it / 0.01 }),
                UnitDef("m", "Meter", "m",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("km", "Kilometer", "km",
                    toBase = { it * 1000.0 },
                    fromBase = { it / 1000.0 }),
                UnitDef("in", "Inch", "in",
                    toBase = { it * 0.0254 },
                    fromBase = { it / 0.0254 }),
                UnitDef("ft", "Foot", "ft",
                    toBase = { it * 0.3048 },
                    fromBase = { it / 0.3048 }),
                UnitDef("yd", "Yard", "yd",
                    toBase = { it * 0.9144 },
                    fromBase = { it / 0.9144 }),
                UnitDef("mi", "Mile", "mi",
                    toBase = { it * 1609.344 },
                    fromBase = { it / 1609.344 })
            )
        ),

        // ── Area (base: m²) ──
        UnitCategory(
            id = "area",
            name = "Area",
            iconName = "square_foot",
            units = listOf(
                UnitDef("mm2", "Square Millimeter", "mm\u00B2",
                    toBase = { it * 1e-6 },
                    fromBase = { it / 1e-6 }),
                UnitDef("cm2", "Square Centimeter", "cm\u00B2",
                    toBase = { it * 1e-4 },
                    fromBase = { it / 1e-4 }),
                UnitDef("m2", "Square Meter", "m\u00B2",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("km2", "Square Kilometer", "km\u00B2",
                    toBase = { it * 1e6 },
                    fromBase = { it / 1e6 }),
                UnitDef("in2", "Square Inch", "in\u00B2",
                    toBase = { it * 0.0254 * 0.0254 },
                    fromBase = { it / (0.0254 * 0.0254) }),
                UnitDef("ft2", "Square Foot", "ft\u00B2",
                    toBase = { it * 0.3048 * 0.3048 },
                    fromBase = { it / (0.3048 * 0.3048) }),
                UnitDef("yd2", "Square Yard", "yd\u00B2",
                    toBase = { it * 0.9144 * 0.9144 },
                    fromBase = { it / (0.9144 * 0.9144) }),
                UnitDef("acre", "Acre", "acre",
                    toBase = { it * 4046.8564224 },
                    fromBase = { it / 4046.8564224 }),
                UnitDef("hectare", "Hectare", "ha",
                    toBase = { it * 10000.0 },
                    fromBase = { it / 10000.0 })
            )
        ),

        // ── Volume (base: m³) ──
        UnitCategory(
            id = "volume",
            name = "Volume",
            iconName = "water_drop",
            units = listOf(
                UnitDef("mL", "Milliliter", "mL",
                    toBase = { it * 1e-6 },
                    fromBase = { it / 1e-6 }),
                UnitDef("L", "Liter", "L",
                    toBase = { it * 0.001 },
                    fromBase = { it / 0.001 }),
                UnitDef("m3", "Cubic Meter", "m\u00B3",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("in3", "Cubic Inch", "in\u00B3",
                    toBase = { it * 0.0254 * 0.0254 * 0.0254 },
                    fromBase = { it / (0.0254 * 0.0254 * 0.0254) }),
                UnitDef("ft3", "Cubic Foot", "ft\u00B3",
                    toBase = { it * 0.3048 * 0.3048 * 0.3048 },
                    fromBase = { it / (0.3048 * 0.3048 * 0.3048) }),
                UnitDef("yd3", "Cubic Yard", "yd\u00B3",
                    toBase = { it * 0.9144 * 0.9144 * 0.9144 },
                    fromBase = { it / (0.9144 * 0.9144 * 0.9144) }),
                UnitDef("usgal", "US Gallon", "US gal",
                    toBase = { it * 0.003785411784 },
                    fromBase = { it / 0.003785411784 }),
                UnitDef("impgal", "Imperial Gallon", "Imp gal",
                    toBase = { it * 0.00454609 },
                    fromBase = { it / 0.00454609 })
            )
        ),

        // ── Mass (base: kg) ──
        UnitCategory(
            id = "mass",
            name = "Mass",
            iconName = "scale",
            units = listOf(
                UnitDef("mg", "Milligram", "mg",
                    toBase = { it * 1e-6 },
                    fromBase = { it / 1e-6 }),
                UnitDef("g", "Gram", "g",
                    toBase = { it * 0.001 },
                    fromBase = { it / 0.001 }),
                UnitDef("kg", "Kilogram", "kg",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("tonne", "Tonne", "t",
                    toBase = { it * 1000.0 },
                    fromBase = { it / 1000.0 }),
                UnitDef("oz", "Ounce", "oz",
                    toBase = { it * 0.028349523125 },
                    fromBase = { it / 0.028349523125 }),
                UnitDef("lb", "Pound", "lb",
                    toBase = { it * 0.45359237 },
                    fromBase = { it / 0.45359237 }),
                UnitDef("uston", "US Ton", "US ton",
                    toBase = { it * 907.18474 },
                    fromBase = { it / 907.18474 })
            )
        ),

        // ── Density (base: kg/m³) ──
        UnitCategory(
            id = "density",
            name = "Density",
            iconName = "density_medium",
            units = listOf(
                UnitDef("kg_m3", "Kilogram per Cubic Meter", "kg/m\u00B3",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("g_cm3", "Gram per Cubic Centimeter", "g/cm\u00B3",
                    toBase = { it * 1000.0 },
                    fromBase = { it / 1000.0 }),
                UnitDef("lb_ft3", "Pound per Cubic Foot", "lb/ft\u00B3",
                    toBase = { it * 0.45359237 / (0.3048 * 0.3048 * 0.3048) },
                    fromBase = { it / (0.45359237 / (0.3048 * 0.3048 * 0.3048)) }),
                UnitDef("lb_yd3", "Pound per Cubic Yard", "lb/yd\u00B3",
                    toBase = { it * 0.45359237 / (0.9144 * 0.9144 * 0.9144) },
                    fromBase = { it / (0.45359237 / (0.9144 * 0.9144 * 0.9144)) })
            )
        ),

        // ── Force (base: N) ──
        UnitCategory(
            id = "force",
            name = "Force",
            iconName = "fitness_center",
            units = listOf(
                UnitDef("N", "Newton", "N",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("kN", "Kilonewton", "kN",
                    toBase = { it * 1000.0 },
                    fromBase = { it / 1000.0 }),
                UnitDef("kgf", "Kilogram-force", "kgf",
                    toBase = { it * 9.80665 },
                    fromBase = { it / 9.80665 }),
                UnitDef("lbf", "Pound-force", "lbf",
                    toBase = { it * 4.4482216152605 },
                    fromBase = { it / 4.4482216152605 }),
                UnitDef("kip", "Kip", "kip",
                    toBase = { it * 4448.2216152605 },
                    fromBase = { it / 4448.2216152605 })
            )
        ),

        // ── Pressure / Stress (base: Pa) ──
        UnitCategory(
            id = "pressure",
            name = "Pressure / Stress",
            iconName = "compress",
            units = listOf(
                UnitDef("Pa", "Pascal", "Pa",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("kPa", "Kilopascal", "kPa",
                    toBase = { it * 1000.0 },
                    fromBase = { it / 1000.0 }),
                UnitDef("MPa", "Megapascal", "MPa",
                    toBase = { it * 1e6 },
                    fromBase = { it / 1e6 }),
                UnitDef("GPa", "Gigapascal", "GPa",
                    toBase = { it * 1e9 },
                    fromBase = { it / 1e9 }),
                UnitDef("psi", "Pound per Square Inch", "psi",
                    toBase = { it * 6894.757293168 },
                    fromBase = { it / 6894.757293168 }),
                UnitDef("psf", "Pound per Square Foot", "psf",
                    toBase = { it * 47.88025898 },
                    fromBase = { it / 47.88025898 }),
                UnitDef("bar", "Bar", "bar",
                    toBase = { it * 100000.0 },
                    fromBase = { it / 100000.0 }),
                UnitDef("atm", "Atmosphere", "atm",
                    toBase = { it * 101325.0 },
                    fromBase = { it / 101325.0 })
            )
        ),

        // ── Moment / Torque (base: N·m) ──
        UnitCategory(
            id = "moment",
            name = "Moment / Torque",
            iconName = "rotate_right",
            units = listOf(
                UnitDef("Nm", "Newton-meter", "N\u00B7m",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("kNm", "Kilonewton-meter", "kN\u00B7m",
                    toBase = { it * 1000.0 },
                    fromBase = { it / 1000.0 }),
                UnitDef("kgfm", "Kilogram-force meter", "kgf\u00B7m",
                    toBase = { it * 9.80665 },
                    fromBase = { it / 9.80665 }),
                UnitDef("lbfft", "Pound-force foot", "lbf\u00B7ft",
                    toBase = { it * 4.4482216152605 * 0.3048 },
                    fromBase = { it / (4.4482216152605 * 0.3048) }),
                UnitDef("kipft", "Kip-foot", "kip\u00B7ft",
                    toBase = { it * 4448.2216152605 * 0.3048 },
                    fromBase = { it / (4448.2216152605 * 0.3048) })
            )
        ),

        // ── Flow (base: m³/s) ──
        UnitCategory(
            id = "flow",
            name = "Flow",
            iconName = "waves",
            units = listOf(
                UnitDef("m3s", "Cubic Meter per Second", "m\u00B3/s",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("Ls", "Liter per Second", "L/s",
                    toBase = { it * 0.001 },
                    fromBase = { it / 0.001 }),
                UnitDef("Lmin", "Liter per Minute", "L/min",
                    toBase = { it * 0.001 / 60.0 },
                    fromBase = { it / (0.001 / 60.0) }),
                UnitDef("galmin", "US Gallon per Minute", "gal/min",
                    toBase = { it * 0.003785411784 / 60.0 },
                    fromBase = { it / (0.003785411784 / 60.0) }),
                UnitDef("cfs", "Cubic Foot per Second", "cfs",
                    toBase = { it * 0.028316846592 },
                    fromBase = { it / 0.028316846592 })
            )
        ),

        // ── Temperature (base: Celsius) ──
        UnitCategory(
            id = "temperature",
            name = "Temperature",
            iconName = "thermostat",
            units = listOf(
                UnitDef("C", "Celsius", "\u00B0C",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("F", "Fahrenheit", "\u00B0F",
                    toBase = { (it - 32.0) * 5.0 / 9.0 },
                    fromBase = { it * 9.0 / 5.0 + 32.0 }),
                UnitDef("K", "Kelvin", "K",
                    toBase = { it - 273.15 },
                    fromBase = { it + 273.15 })
            )
        ),

        // ── Slope / Grade (base: ratio) ──
        UnitCategory(
            id = "slope",
            name = "Slope / Grade",
            iconName = "trending_up",
            units = listOf(
                UnitDef("ratio", "Ratio", "ratio",
                    toBase = { it },
                    fromBase = { it }),
                UnitDef("percent", "Percent", "%",
                    toBase = { it / 100.0 },
                    fromBase = { it * 100.0 }),
                UnitDef("permille", "Per mille", "\u2030",
                    toBase = { it / 1000.0 },
                    fromBase = { it * 1000.0 }),
                UnitDef("degrees", "Degrees", "\u00B0",
                    toBase = { tan(it * Math.PI / 180.0) },
                    fromBase = { atan(it) * 180.0 / Math.PI }),
                UnitDef("one_in_n", "1 in n", "1:n",
                    toBase = { if (it != 0.0) 1.0 / it else 0.0 },
                    fromBase = { if (it != 0.0) 1.0 / it else 0.0 })
            )
        )
    )

    fun findCategory(id: String): UnitCategory? =
        categories.find { it.id == id }

    fun findUnit(categoryId: String, unitId: String): UnitDef? =
        findCategory(categoryId)?.units?.find { it.id == unitId }
}
