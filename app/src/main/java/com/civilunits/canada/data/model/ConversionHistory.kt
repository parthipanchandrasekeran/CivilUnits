package com.civilunits.canada.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class ConversionHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categoryId: String,
    val fromUnitId: String,
    val toUnitId: String,
    val inputValue: Double,
    val outputValue: Double,
    val timestamp: Long
)
