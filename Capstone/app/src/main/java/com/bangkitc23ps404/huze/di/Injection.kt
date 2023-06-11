package com.bangkitc23ps404.huze.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bangkitc23ps404.huze.data.Repository
import com.bangkitc23ps404.huze.ui.local.AuthDataStore
import com.bangkitc23ps404.huze.ui.local.LocaleDataStore
import com.bangkitc23ps404.huze.data.network.ApiConfig

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")
object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(
            apiService,
            AuthDataStore.getInstance(context.dataStore),
            LocaleDataStore.getInstance(context.dataStore)
        )
    }
}