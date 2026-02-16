package com.civilunits.canada.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civilunits.canada.data.model.ConversionHistory
import com.civilunits.canada.data.repository.HistoryRepository
import com.civilunits.canada.data.repository.UnitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val unitRepository: UnitRepository
) : ViewModel() {

    val history: StateFlow<List<ConversionHistory>> =
        historyRepository.getRecent()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun deleteEntry(id: Int) {
        viewModelScope.launch {
            historyRepository.deleteById(id)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            historyRepository.clearAll()
        }
    }

    fun getUnitDisplayName(categoryId: String, unitId: String): String {
        val unit = unitRepository.getUnit(categoryId, unitId)
        return unit?.let { "${it.name} (${it.symbol})" } ?: unitId
    }
}
