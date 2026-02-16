package com.civilunits.canada.ui.quickcivil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civilunits.canada.data.model.PrecisionMode
import com.civilunits.canada.data.model.convert
import com.civilunits.canada.data.repository.PreferencesRepository
import com.civilunits.canada.data.repository.UnitRepository
import com.civilunits.canada.util.Formatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class QuickPair(
    val categoryId: String,
    val fromId: String,
    val toId: String,
    val label: String
)

data class QuickConversionState(
    val pair: QuickPair,
    val inputText: String = "",
    val resultText: String = ""
)

@HiltViewModel
class QuickCivilViewModel @Inject constructor(
    private val unitRepository: UnitRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    companion object {
        val DEFAULT_PAIRS = listOf(
            QuickPair("pressure", "MPa", "psi", "MPa \u2192 psi"),
            QuickPair("pressure", "kPa", "psf", "kPa \u2192 psf"),
            QuickPair("force", "kN", "lbf", "kN \u2192 lbf"),
            QuickPair("force", "kN", "kip", "kN \u2192 kip"),
            QuickPair("moment", "kNm", "lbfft", "kN\u00B7m \u2192 lbf\u00B7ft"),
            QuickPair("density", "kg_m3", "lb_ft3", "kg/m\u00B3 \u2192 lb/ft\u00B3"),
            QuickPair("length", "mm", "in", "mm \u2192 in"),
            QuickPair("length", "m", "ft", "m \u2192 ft"),
            QuickPair("area", "m2", "ft2", "m\u00B2 \u2192 ft\u00B2"),
            QuickPair("flow", "Ls", "galmin", "L/s \u2192 gpm")
        )
    }

    private var precisionMode: PrecisionMode = PrecisionMode.Auto

    private val _conversions = MutableStateFlow(
        DEFAULT_PAIRS.map { QuickConversionState(pair = it) }
    )
    val conversions: StateFlow<List<QuickConversionState>> = _conversions.asStateFlow()

    init {
        viewModelScope.launch {
            preferencesRepository.precisionMode.collect { mode ->
                precisionMode = mode
                // Reconvert all existing inputs with the new precision
                _conversions.update { list ->
                    list.map { state ->
                        if (state.inputText.isNotBlank()) {
                            state.copy(resultText = performConversion(state.pair, state.inputText))
                        } else {
                            state
                        }
                    }
                }
            }
        }
    }

    fun onInputChange(index: Int, text: String) {
        _conversions.update { list ->
            list.toMutableList().also { mutable ->
                val current = mutable[index]
                val result = performConversion(current.pair, text)
                mutable[index] = current.copy(inputText = text, resultText = result)
            }
        }
    }

    private fun performConversion(pair: QuickPair, inputText: String): String {
        val input = inputText.toDoubleOrNull() ?: return ""
        val fromUnit = unitRepository.getUnit(pair.categoryId, pair.fromId) ?: return ""
        val toUnit = unitRepository.getUnit(pair.categoryId, pair.toId) ?: return ""
        val result = convert(input, fromUnit, toUnit)
        return Formatter.formatResult(result, precisionMode)
    }
}
