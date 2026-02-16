package com.civilunits.canada.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteConversion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categoryId: String,
    val fromUnitId: String,
    val toUnitId: String,
    val createdAt: Long
)
