package com.civilunits.canada.data.units

import org.junit.Assert.*
import org.junit.Test

class RebarDataTest {

    @Test
    fun `rebar table has 9 entries`() {
        assertEquals(9, RebarData.bars.size)
    }

    @Test
    fun `bar numbers range from 3 to 11`() {
        assertEquals(3, RebarData.bars.first().usBarNumber)
        assertEquals(11, RebarData.bars.last().usBarNumber)
    }

    @Test
    fun `bar 3 has correct diameter`() {
        val bar3 = RebarData.bars.first { it.usBarNumber == 3 }
        assertEquals(0.375, bar3.diameterIn, 0.001)
        assertEquals(9.525, bar3.diameterMm, 0.01)
    }

    @Test
    fun `bar 8 has correct area`() {
        val bar8 = RebarData.bars.first { it.usBarNumber == 8 }
        assertEquals(0.79, bar8.areaIn2, 0.01)
        assertEquals(507.0, bar8.areaMm2, 1.0)
    }

    @Test
    fun `bar 11 has correct metric size`() {
        val bar11 = RebarData.bars.first { it.usBarNumber == 11 }
        assertEquals(36, bar11.metricSize)
    }

    @Test
    fun `all bars have positive dimensions`() {
        RebarData.bars.forEach { bar ->
            assertTrue("Bar #${bar.usBarNumber} diameterIn", bar.diameterIn > 0)
            assertTrue("Bar #${bar.usBarNumber} diameterMm", bar.diameterMm > 0)
            assertTrue("Bar #${bar.usBarNumber} areaIn2", bar.areaIn2 > 0)
            assertTrue("Bar #${bar.usBarNumber} areaMm2", bar.areaMm2 > 0)
        }
    }

    @Test
    fun `metric sizes are monotonically increasing`() {
        val sizes = RebarData.bars.map { it.metricSize }
        for (i in 1 until sizes.size) {
            assertTrue("Metric sizes should increase", sizes[i] > sizes[i - 1])
        }
    }

    @Test
    fun `areas are monotonically increasing`() {
        val areas = RebarData.bars.map { it.areaMm2 }
        for (i in 1 until areas.size) {
            assertTrue("Areas should increase", areas[i] > areas[i - 1])
        }
    }
}
