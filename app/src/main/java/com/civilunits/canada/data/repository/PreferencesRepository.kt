package com.civilunits.canada.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.civilunits.canada.data.model.GallonMode
import com.civilunits.canada.data.model.PrecisionMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val KEY_PRECISION_MODE = stringPreferencesKey("precision_mode")
        val KEY_GALLON_MODE = stringPreferencesKey("gallon_mode")
        val KEY_DEFAULT_CATEGORY = stringPreferencesKey("default_category_id")
    }

    val precisionMode: Flow<PrecisionMode> = dataStore.data.map { prefs ->
        prefs[KEY_PRECISION_MODE]?.let { name ->
            PrecisionMode.entries.firstOrNull { it.name == name }
        } ?: PrecisionMode.Auto
    }

    val gallonMode: Flow<GallonMode> = dataStore.data.map { prefs ->
        prefs[KEY_GALLON_MODE]?.let { name ->
            GallonMode.entries.firstOrNull { it.name == name }
        } ?: GallonMode.US
    }

    val defaultCategoryId: Flow<String?> = dataStore.data.map { prefs ->
        prefs[KEY_DEFAULT_CATEGORY]
    }

    suspend fun setPrecisionMode(mode: PrecisionMode) {
        dataStore.edit { prefs ->
            prefs[KEY_PRECISION_MODE] = mode.name
        }
    }

    suspend fun setGallonMode(mode: GallonMode) {
        dataStore.edit { prefs ->
            prefs[KEY_GALLON_MODE] = mode.name
        }
    }

    suspend fun setDefaultCategoryId(categoryId: String?) {
        dataStore.edit { prefs ->
            if (categoryId != null) {
                prefs[KEY_DEFAULT_CATEGORY] = categoryId
            } else {
                prefs.remove(KEY_DEFAULT_CATEGORY)
            }
        }
    }
}
