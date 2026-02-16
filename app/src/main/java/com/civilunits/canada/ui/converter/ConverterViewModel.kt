package com.civilunits.canada.ui.converter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civilunits.canada.data.model.ConversionHistory
import com.civilunits.canada.data.model.PrecisionMode
import com.civilunits.canada.data.model.UnitCategory
import com.civilunits.canada.data.model.UnitDef
import com.civilunits.canada.data.model.convert
import com.civilunits.canada.data.repository.FavoriteRepository
import com.civilunits.canada.data.repository.HistoryRepository
import com.civilunits.canada.data.repository.PreferencesRepository
import com.civilunits.canada.data.repository.UnitRepository
import com.civilunits.canada.navigation.Route
import com.civilunits.canada.util.Formatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ConverterUiState(
    val category: UnitCategory? = null,
    val fromUnit: UnitDef? = null,
    val toUnit: UnitDef? = null,
    val inputText: String = "",
    val resultText: String = "",
    val isFavorite: Boolean = false,
    val precisionMode: PrecisionMode = PrecisionMode.Auto
)

@HiltViewModel
class ConverterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val unitRepository: UnitRepository,
    private val favoriteRepository: FavoriteRepository,
    private val historyRepository: HistoryRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val categoryId: String = checkNotNull(
        savedStateHandle[Route.Converter.ARG_CATEGORY_ID]
    )

    private val _uiState = MutableStateFlow(ConverterUiState())
    val uiState: StateFlow<ConverterUiState> = _uiState.asStateFlow()

    private var historyJob: Job? = null

    init {
        val category = unitRepository.getCategoryById(categoryId)
        val fromUnit = category?.units?.getOrNull(0)
        val toUnit = category?.units?.getOrNull(1)

        _uiState.update {
            it.copy(
                category = category,
                fromUnit = fromUnit,
                toUnit = toUnit
            )
        }

        // Observe precision mode from preferences
        viewModelScope.launch {
            preferencesRepository.precisionMode.collect { mode ->
                _uiState.update { it.copy(precisionMode = mode) }
                reconvert()
            }
        }

        // Observe favorite status
        observeFavoriteStatus()
    }

    private fun observeFavoriteStatus() {
        viewModelScope.launch {
            val state = _uiState.value
            val from = state.fromUnit ?: return@launch
            val to = state.toUnit ?: return@launch
            favoriteRepository.exists(categoryId, from.id, to.id).collect { exists ->
                _uiState.update { it.copy(isFavorite = exists) }
            }
        }
    }

    fun onInputChange(text: String) {
        _uiState.update { it.copy(inputText = text) }
        reconvert()
        scheduleSaveHistory()
    }

    fun onFromUnitChange(unitId: String) {
        val unit = _uiState.value.category?.units?.firstOrNull { it.id == unitId } ?: return
        _uiState.update { it.copy(fromUnit = unit) }
        reconvert()
        observeFavoriteStatus()
    }

    fun onToUnitChange(unitId: String) {
        val unit = _uiState.value.category?.units?.firstOrNull { it.id == unitId } ?: return
        _uiState.update { it.copy(toUnit = unit) }
        reconvert()
        observeFavoriteStatus()
    }

    fun swapUnits() {
        _uiState.update {
            it.copy(fromUnit = it.toUnit, toUnit = it.fromUnit)
        }
        reconvert()
        observeFavoriteStatus()
    }

    fun toggleFavorite() {
        val state = _uiState.value
        val from = state.fromUnit ?: return
        val to = state.toUnit ?: return
        viewModelScope.launch {
            favoriteRepository.toggle(categoryId, from.id, to.id)
        }
    }

    fun setPrecision(mode: PrecisionMode) {
        viewModelScope.launch {
            preferencesRepository.setPrecisionMode(mode)
        }
    }

    fun getResultForCopy(): String {
        val state = _uiState.value
        val from = state.fromUnit ?: return ""
        val to = state.toUnit ?: return ""
        return "${state.inputText} ${from.symbol} = ${state.resultText} ${to.symbol}"
    }

    private fun reconvert() {
        val state = _uiState.value
        val from = state.fromUnit ?: return
        val to = state.toUnit ?: return
        val input = state.inputText.toDoubleOrNull()

        if (input == null || state.inputText.isBlank()) {
            _uiState.update { it.copy(resultText = "") }
            return
        }

        val result = convert(input, from, to)
        val formatted = Formatter.formatResult(result, state.precisionMode)
        _uiState.update { it.copy(resultText = formatted) }
    }

    private fun scheduleSaveHistory() {
        historyJob?.cancel()
        historyJob = viewModelScope.launch {
            delay(500L)
            val state = _uiState.value
            val from = state.fromUnit ?: return@launch
            val to = state.toUnit ?: return@launch
            val input = state.inputText.toDoubleOrNull() ?: return@launch
            val output = convert(input, from, to)

            historyRepository.add(
                ConversionHistory(
                    categoryId = categoryId,
                    fromUnitId = from.id,
                    toUnitId = to.id,
                    inputValue = input,
                    outputValue = output,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }
}
