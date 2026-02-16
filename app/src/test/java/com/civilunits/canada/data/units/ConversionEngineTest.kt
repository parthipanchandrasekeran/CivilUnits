package com.civilunits.canada.data.units

import com.civilunits.canada.data.model.convert
import org.junit.Assert.assertEquals
import org.junit.Test

class ConversionEngineTest {

    private fun unit(categoryId: String, unitId: String) =
        UnitDefinitions.findUnit(categoryId, unitId)
            ?: throw IllegalArgumentException("Unit not found: $categoryId/$unitId")

    private fun assertConversion(
        categoryId: String,
        fromId: String,
        toId: String,
        input: Double,
        expected: Double,
        tolerance: Double = 0.01
    ) {
        val from = unit(categoryId, fromId)
        val to = unit(categoryId, toId)
        val result = convert(input, from, to)
        assertEquals("$fromId -> $toId ($input)", expected, result, tolerance)
    }

    @Test
    fun `MPa to psi`() {
        assertConversion("pressure", "MPa", "psi", 1.0, 145.038, 0.01)
    }

    @Test
    fun `psi to MPa`() {
        assertConversion("pressure", "psi", "MPa", 145.038, 1.0, 0.001)
    }

    @Test
    fun `kPa to psf`() {
        assertConversion("pressure", "kPa", "psf", 1.0, 20.8854, 0.01)
    }

    @Test
    fun `kN to lbf`() {
        assertConversion("force", "kN", "lbf", 1.0, 224.809, 0.01)
    }

    @Test
    fun `lbf to kN`() {
        assertConversion("force", "lbf", "kN", 224.809, 1.0, 0.001)
    }

    @Test
    fun `kg per m3 to lb per ft3`() {
        assertConversion("density", "kg_m3", "lb_ft3", 1.0, 0.06243, 0.001)
    }

    @Test
    fun `lb per ft3 to kg per m3`() {
        assertConversion("density", "lb_ft3", "kg_m3", 0.06243, 1.0, 0.01)
    }

    @Test
    fun `m3 per s to cfs`() {
        assertConversion("flow", "m3s", "cfs", 1.0, 35.3147, 0.01)
    }

    @Test
    fun `cfs to m3 per s`() {
        assertConversion("flow", "cfs", "m3s", 35.3147, 1.0, 0.001)
    }

    @Test
    fun `kNm to lbf ft`() {
        assertConversion("moment", "kNm", "lbfft", 1.0, 737.562, 0.01)
    }

    @Test
    fun `100 C to F`() {
        assertConversion("temperature", "C", "F", 100.0, 212.0, 0.01)
    }

    @Test
    fun `32 F to C`() {
        assertConversion("temperature", "F", "C", 32.0, 0.0, 0.01)
    }

    @Test
    fun `25_4 mm to inches`() {
        assertConversion("length", "mm", "in", 25.4, 1.0, 0.0001)
    }

    @Test
    fun `1 m to ft`() {
        assertConversion("length", "m", "ft", 1.0, 3.28084, 0.0001)
    }

    @Test
    fun `1 m3 to yd3`() {
        assertConversion("volume", "m3", "yd3", 1.0, 1.30795, 0.001)
    }

    @Test
    fun `L per s to gpm`() {
        assertConversion("flow", "Ls", "galmin", 1.0, 15.8503, 0.01)
    }

    @Test
    fun `slope 1 percent to degrees`() {
        assertConversion("slope", "percent", "degrees", 1.0, 0.5729, 0.001)
    }

    @Test
    fun `round trip identity - pressure`() {
        val from = unit("pressure", "MPa")
        val to = unit("pressure", "psi")
        val result = convert(convert(42.0, from, to), to, from)
        assertEquals(42.0, result, 0.0001)
    }

    @Test
    fun `round trip identity - temperature`() {
        val from = unit("temperature", "C")
        val to = unit("temperature", "F")
        val result = convert(convert(-40.0, from, to), to, from)
        assertEquals(-40.0, result, 0.0001)
    }

    // ── Area ──

    @Test
    fun `m2 to ft2`() {
        assertConversion("area", "m2", "ft2", 1.0, 10.7639, 0.01)
    }

    @Test
    fun `acre to hectare`() {
        assertConversion("area", "acre", "hectare", 1.0, 0.4047, 0.001)
    }

    // ── Volume ──

    @Test
    fun `L to US gal`() {
        assertConversion("volume", "L", "usgal", 1.0, 0.26417, 0.001)
    }

    @Test
    fun `ft3 to L`() {
        assertConversion("volume", "ft3", "L", 1.0, 28.3168, 0.01)
    }

    // ── Mass ──

    @Test
    fun `kg to lb`() {
        assertConversion("mass", "kg", "lb", 1.0, 2.20462, 0.001)
    }

    @Test
    fun `tonne to US ton`() {
        assertConversion("mass", "tonne", "uston", 1.0, 1.10231, 0.001)
    }

    // ── Identity (same-unit) ──

    @Test
    fun `m to m identity`() {
        assertConversion("length", "m", "m", 42.0, 42.0, 0.0)
    }

    @Test
    fun `Pa to Pa identity`() {
        assertConversion("pressure", "Pa", "Pa", 100.0, 100.0, 0.0)
    }

    // ── Zero ──

    @Test
    fun `0 mm to 0 in`() {
        assertConversion("length", "mm", "in", 0.0, 0.0, 0.0)
    }

    @Test
    fun `0 C to 32 F`() {
        assertConversion("temperature", "C", "F", 0.0, 32.0, 0.01)
    }

    // ── Negative ──

    @Test
    fun `minus 40 C to minus 40 F`() {
        assertConversion("temperature", "C", "F", -40.0, -40.0, 0.01)
    }

    @Test
    fun `negative kN to lbf`() {
        assertConversion("force", "kN", "lbf", -5.0, -1124.045, 0.01)
    }

    // ── Slope ──

    @Test
    fun `45 degrees to 100 percent`() {
        assertConversion("slope", "degrees", "percent", 45.0, 100.0, 0.01)
    }

    @Test
    fun `100 percent to ratio 1`() {
        assertConversion("slope", "percent", "ratio", 100.0, 1.0, 0.0001)
    }

    // ── Round-trip ──

    @Test
    fun `round trip kg to lb`() {
        val from = unit("mass", "kg")
        val to = unit("mass", "lb")
        val result = convert(convert(75.0, from, to), to, from)
        assertEquals(75.0, result, 0.0001)
    }
}
