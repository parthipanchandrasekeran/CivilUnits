package com.civilunits.canada.data.repository

import com.civilunits.canada.data.local.HistoryDao
import com.civilunits.canada.data.model.ConversionHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(
    private val dao: HistoryDao
) {
    fun getRecent(): Flow<List<ConversionHistory>> = dao.getRecent()

    suspend fun add(entry: ConversionHistory) = dao.insert(entry)

    suspend fun deleteById(id: Int) = dao.deleteById(id)

    suspend fun clearAll() = dao.clearAll()
}
