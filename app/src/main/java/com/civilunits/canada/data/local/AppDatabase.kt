package com.civilunits.canada.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.civilunits.canada.data.model.FavoriteConversion
import com.civilunits.canada.data.model.ConversionHistory

@Database(
    entities = [FavoriteConversion::class, ConversionHistory::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun historyDao(): HistoryDao
}
