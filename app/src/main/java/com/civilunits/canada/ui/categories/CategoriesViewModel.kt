package com.civilunits.canada.ui.categories

import androidx.lifecycle.ViewModel
import com.civilunits.canada.data.model.UnitCategory
import com.civilunits.canada.data.repository.UnitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val unitRepository: UnitRepository
) : ViewModel() {

    val categories: List<UnitCategory>
        get() = unitRepository.categories
}
