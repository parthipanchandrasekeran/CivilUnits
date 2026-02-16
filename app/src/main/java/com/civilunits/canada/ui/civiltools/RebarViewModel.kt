package com.civilunits.canada.ui.civiltools

import androidx.lifecycle.ViewModel
import com.civilunits.canada.data.units.RebarData
import com.civilunits.canada.data.units.RebarInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RebarViewModel @Inject constructor() : ViewModel() {

    private val _bars = MutableStateFlow(RebarData.bars)
    val bars: StateFlow<List<RebarInfo>> = _bars.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        _bars.value = if (query.isBlank()) {
            RebarData.bars
        } else {
            RebarData.bars.filter { bar ->
                bar.usBarNumber.toString() == query.trim() ||
                    bar.metricSize.toString() == query.trim() ||
                    "#${bar.usBarNumber}" == query.trim() ||
                    bar.usBarNumber.toString().contains(query.trim()) ||
                    bar.metricSize.toString().contains(query.trim())
            }
        }
    }

    fun findByUsBarNumber(barNumber: Int): RebarInfo? =
        RebarData.bars.firstOrNull { it.usBarNumber == barNumber }

    fun findByMetricSize(metricSize: Int): RebarInfo? =
        RebarData.bars.firstOrNull { it.metricSize == metricSize }
}
