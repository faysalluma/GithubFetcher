package com.example.testmobile.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext val context: Context) {
    companion object {
        private val PACKAGE_KEY = stringPreferencesKey("package")
        private val MODE_KEY = stringPreferencesKey("mode")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val configFlow: Flow<Pair<String,String>> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            Pair(
                preferences[PACKAGE_KEY] ?: "",
                preferences[MODE_KEY]?: ""
            )
        }

    suspend fun setConfig(packag: String, mode: String) {
        context.dataStore.edit { datastore ->
            datastore[PACKAGE_KEY] = packag
            datastore[MODE_KEY] = mode
        }
    }
}