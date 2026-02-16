package com.civilunits.canada.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.civilunits.canada.data.model.GallonMode
import com.civilunits.canada.data.model.PrecisionMode
import com.civilunits.canada.data.repository.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    val precisionMode: StateFlow<PrecisionMode> =
        preferencesRepository.precisionMode
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PrecisionMode.Auto
            )

    val gallonMode: StateFlow<GallonMode> =
        preferencesRepository.gallonMode
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = GallonMode.US
            )

    fun setPrecisionMode(mode: PrecisionMode) {
        viewModelScope.launch {
            preferencesRepository.setPrecisionMode(mode)
        }
    }

    fun setGallonMode(mode: GallonMode) {
        viewModelScope.launch {
            preferencesRepository.setGallonMode(mode)
        }
    }
}
