package com.bangkitc23ps404.huze.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkitc23ps404.huze.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel (private val repository: Repository): ViewModel() {
    fun getToken() = repository.getToken().asLiveData(Dispatchers.IO)

    fun clearToken() {
        viewModelScope.launch {
            repository.clearToken()
        }
    }
}
