package ru.adavydova.searchflights_data.usecase

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.adavydova.searchflights_data.utils.Constants

private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(Constants.USER_SETTINGS)

private object PreferencesKeys{
    val USER_SETTINGS_FROM_THE_COUNTRY = stringPreferencesKey(name = Constants.USER_SETTINGS_FROM_THE_COUNTRY)
}


class SavingTheCountryUseCase (private val context:Context) {
    suspend operator fun invoke(country: String) {
        context.dataStore.edit {
            it[PreferencesKeys.USER_SETTINGS_FROM_THE_COUNTRY] = country
        }
    }
}

class ReadTheSavedCountry(private val context: Context) {
    operator fun invoke(): Flow<String?> {
        return context.dataStore.data.map { preferences: Preferences ->
            preferences[PreferencesKeys.USER_SETTINGS_FROM_THE_COUNTRY]
        }
    }
}