package com.civilunits.canada.data.units

data class RebarInfo(
    val usBarNumber: Int,
    val metricSize: Int,
    val diameterIn: Double,
    val diameterMm: Double,
    val areaIn2: Double,
    val areaMm2: Double
)

object RebarData {
    val bars = listOf(
        RebarInfo(3, 10, 0.375, 9.525, 0.11, 71.0),
        RebarInfo(4, 13, 0.500, 12.7, 0.20, 129.0),
        RebarInfo(5, 16, 0.625, 15.875, 0.31, 199.0),
        RebarInfo(6, 19, 0.750, 19.05, 0.44, 284.0),
        RebarInfo(7, 22, 0.875, 22.225, 0.60, 387.0),
        RebarInfo(8, 25, 1.000, 25.4, 0.79, 507.0),
        RebarInfo(9, 29, 1.128, 28.651, 1.00, 645.0),
        RebarInfo(10, 32, 1.270, 32.258, 1.27, 819.0),
        RebarInfo(11, 36, 1.410, 35.814, 1.56, 1006.0)
    )
}
