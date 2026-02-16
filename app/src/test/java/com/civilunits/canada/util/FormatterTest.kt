package com.civilunits.canada.util

import com.civilunits.canada.data.model.PrecisionMode
import org.junit.Assert.assertEquals
import org.junit.Test

class FormatterTest {

    // ── Auto mode ──

    @Test
    fun `auto - zero`() {
        assertEquals("0", Formatter.formatResult(0.0, PrecisionMode.Auto))
    }

    @Test
    fun `auto - positive integer value`() {
        assertEquals("42", Formatter.formatResult(42.0, PrecisionMode.Auto))
    }

    @Test
    fun `auto - strips trailing zeros`() {
        assertEquals("3.5", Formatter.formatResult(3.50, PrecisionMode.Auto))
    }

    @Test
    fun `auto - large number uses scientific`() {
        val result = Formatter.formatResult(1.5e12, PrecisionMode.Auto)
        assert(result.contains("E", ignoreCase = true)) { "Expected scientific notation, got: $result" }
    }

    @Test
    fun `auto - very small number uses scientific`() {
        val result = Formatter.formatResult(3.2e-9, PrecisionMode.Auto)
        assert(result.contains("E", ignoreCase = true)) { "Expected scientific notation, got: $result" }
    }

    @Test
    fun `auto - negative value`() {
        val result = Formatter.formatResult(-7.25, PrecisionMode.Auto)
        assertEquals("-7.25", result)
    }

    // ── Fixed precision ──

    @Test
    fun `Dec2 - rounds to 2 decimals`() {
        assertEquals("3.14", Formatter.formatResult(3.14159, PrecisionMode.Dec2))
    }

    @Test
    fun `Dec4 - rounds to 4 decimals`() {
        assertEquals("3.1416", Formatter.formatResult(3.14159, PrecisionMode.Dec4))
    }

    @Test
    fun `Dec6 - rounds to 6 decimals`() {
        assertEquals("3.141590", Formatter.formatResult(3.14159, PrecisionMode.Dec6))
    }

    @Test
    fun `Dec2 - zero shows trailing zeros`() {
        assertEquals("0.00", Formatter.formatResult(0.0, PrecisionMode.Dec2))
    }

    @Test
    fun `Dec4 - negative with fixed`() {
        assertEquals("-1.2346", Formatter.formatResult(-1.23456, PrecisionMode.Dec4))
    }

    // ── Edge cases ──

    @Test
    fun `NaN`() {
        assertEquals("NaN", Formatter.formatResult(Double.NaN, PrecisionMode.Auto))
    }

    @Test
    fun `positive infinity`() {
        assertEquals("\u221E", Formatter.formatResult(Double.POSITIVE_INFINITY, PrecisionMode.Auto))
    }

    @Test
    fun `negative infinity`() {
        assertEquals("-\u221E", Formatter.formatResult(Double.NEGATIVE_INFINITY, PrecisionMode.Auto))
    }

    @Test
    fun `NaN with fixed precision`() {
        assertEquals("NaN", Formatter.formatResult(Double.NaN, PrecisionMode.Dec2))
    }

    @Test
    fun `very large in Dec2`() {
        val result = Formatter.formatResult(1e15, PrecisionMode.Dec2)
        // Should format as fixed decimal, not scientific
        assert(!result.contains("E", ignoreCase = true)) { "Expected non-scientific, got: $result" }
    }
}
