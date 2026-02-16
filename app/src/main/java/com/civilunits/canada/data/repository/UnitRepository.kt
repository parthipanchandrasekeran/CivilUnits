package com.civilunits.canada.data.repository

import com.civilunits.canada.data.units.UnitDefinitions
import com.civilunits.canada.data.model.UnitCategory
import com.civilunits.canada.data.model.UnitDef
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnitRepository @Inject constructor() {

    val categories: List<UnitCategory>
        get() = UnitDefinitions.categories

    fun getCategoryById(id: String): UnitCategory? =
        UnitDefinitions.findCategory(id)

    fun getUnit(categoryId: String, unitId: String): UnitDef? =
        UnitDefinitions.findUnit(categoryId, unitId)

    fun getUnitsForCategory(categoryId: String): List<UnitDef> =
        getCategoryById(categoryId)?.units.orEmpty()
}
