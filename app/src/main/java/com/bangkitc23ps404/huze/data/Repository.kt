package com.bangkitc23ps404.huze.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bangkitc23ps404.huze.ui.local.AuthDataStore
import com.bangkitc23ps404.huze.ui.local.LocaleDataStore
import com.bangkitc23ps404.huze.data.model.LoginResponse
import com.bangkitc23ps404.huze.data.model.RegisterModel
import com.bangkitc23ps404.huze.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class Repository(
    private val apiService: ApiService,
    private val authDataStore: AuthDataStore,
    private val localeDataStore: LocaleDataStore
) {
    fun getToken(): Flow<String?> = authDataStore.getToken()

    private suspend fun saveToken(token: String) {
        authDataStore.saveToken(token)
    }

    suspend fun clearToken() {
        authDataStore.clearToken()
    }

    fun getLocale(): Flow<String> = localeDataStore.getLocaleSetting()

    suspend fun saveLocale(locale: String) {
        localeDataStore.saveLocaleSetting(locale)
    }

    fun getUserLogin(email: String, password: String): LiveData<Result<LoginResponse>> =
        liveData(Dispatchers.IO) {
            emit(Result.Loading)
            try {
                val response = apiService.login(email, password)
                saveToken(response.data.accessToken)
                emit(Result.Success(response))
            } catch (e: Exception) {
                emit(Result.Error(e.message.toString()))
            }
        }

    fun saveUserRegister(
        name: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterModel>> = liveData(Dispatchers.IO) {
        emit(Result.Loading)
        try {
            val response = apiService.register(name, email, password)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    private fun generateBearerToken(token: String): String {
        return if (token.contains("bearer", true)) {
            token
        } else {
            "Bearer $token"
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,
            prefDataStore: AuthDataStore,
            localeDataStore: LocaleDataStore
        ): Repository = instance ?: synchronized(this) {
            instance ?: Repository(apiService, prefDataStore, localeDataStore)
        }.also { instance = it }
    }
}