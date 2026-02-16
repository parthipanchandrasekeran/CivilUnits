package com.civilunits.canada.data.repository

import com.civilunits.canada.data.local.FavoriteDao
import com.civilunits.canada.data.model.FavoriteConversion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(
    private val dao: FavoriteDao
) {
    fun getAll(): Flow<List<FavoriteConversion>> = dao.getAll()

    fun exists(categoryId: String, fromId: String, toId: String): Flow<Boolean> =
        dao.exists(categoryId, fromId, toId)

    suspend fun toggle(categoryId: String, fromId: String, toId: String) {
        val existing = dao.find(categoryId, fromId, toId)
        if (existing != null) {
            dao.delete(existing)
        } else {
            dao.insert(
                FavoriteConversion(
                    categoryId = categoryId,
                    fromUnitId = fromId,
                    toUnitId = toId,
                    createdAt = System.currentTimeMillis()
                )
            )
        }
    }

    suspend fun remove(favorite: FavoriteConversion) = dao.delete(favorite)
}
