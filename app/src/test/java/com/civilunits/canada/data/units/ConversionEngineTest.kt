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
}
