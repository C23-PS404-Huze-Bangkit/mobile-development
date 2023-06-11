package com.bangkitc23ps404.huze.ui.login

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.databinding.ActivityLoginBinding
import com.bangkitc23ps404.huze.utils.ViewModelFactory
import com.bangkitc23ps404.huze.data.Result
import com.bangkitc23ps404.huze.ui.home.HomeActivity
import com.bangkitc23ps404.huze.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Huze)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val loginViewModel: LoginViewModel by viewModels {
            factory
        }
        loginViewModel.apply {
            getToken().observe(this@LoginActivity) {
                if (it != null) {
                    toHome()
                }
            }
        }
        binding.apply {
            btnLogin.setOnClickListener {
                val email = binding.edLoginEmail.text.toString().trim()
                val password = binding.edLoginPassword.text.toString().trim()
                loginViewModel.getUserLogin(email, password).observe(this@LoginActivity) {
                    when (it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Success -> {
                            showLoading(false)
                            toHome()
                        }
                        is Result.Error -> {
                            Toast.makeText(
                                this@LoginActivity,
                                it.error,
//                                R.string.login_failed,
                                Toast.LENGTH_SHORT
                            ).show()
                            showLoading(false)
                        }
                    }
                }
            }

            tvRegister.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        supportActionBar?.hide()
    }

    private fun toHome() {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}