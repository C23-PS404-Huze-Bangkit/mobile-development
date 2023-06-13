package com.bangkitc23ps404.huze.ui.register

import androidx.lifecycle.ViewModel
import com.bangkitc23ps404.huze.data.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun saveUserRegister(email: String, password: String, fullname: String ) =
        repository.saveUserRegister(email, password,fullname)
}