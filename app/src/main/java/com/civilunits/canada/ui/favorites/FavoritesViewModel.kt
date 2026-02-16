package com.civilunits.canada.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civilunits.canada.data.model.FavoriteConversion
import com.civilunits.canada.data.repository.FavoriteRepository
import com.civilunits.canada.data.repository.UnitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    private val unitRepository: UnitRepository
) : ViewModel() {

    val favorites: StateFlow<List<FavoriteConversion>> =
        favoriteRepository.getAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun remove(favorite: FavoriteConversion) {
        viewModelScope.launch {
            favoriteRepository.remove(favorite)
        }
    }

    fun getUnitDisplayName(categoryId: String, unitId: String): String {
        val unit = unitRepository.getUnit(categoryId, unitId)
        return unit?.let { "${it.name} (${it.symbol})" } ?: unitId
    }
}
