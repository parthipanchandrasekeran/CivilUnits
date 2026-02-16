package com.civilunits.canada.data.local

import androidx.room.*
import com.civilunits.canada.data.model.FavoriteConversion
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteConversion)

    @Delete
    suspend fun delete(favorite: FavoriteConversion)

    @Query("SELECT * FROM favorites ORDER BY createdAt DESC")
    fun getAll(): Flow<List<FavoriteConversion>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE categoryId = :categoryId AND fromUnitId = :fromId AND toUnitId = :toId)")
    fun exists(categoryId: String, fromId: String, toId: String): Flow<Boolean>

    @Query("SELECT * FROM favorites WHERE categoryId = :categoryId AND fromUnitId = :fromId AND toUnitId = :toId LIMIT 1")
    suspend fun find(categoryId: String, fromId: String, toId: String): FavoriteConversion?
}
