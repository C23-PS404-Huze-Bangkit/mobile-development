package com.bangkitc23ps404.huze.ui.register


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bangkitc23ps404.huze.R
import com.bangkitc23ps404.huze.databinding.ActivityRegisterBinding
import com.bangkitc23ps404.huze.data.Result
import com.bangkitc23ps404.huze.ui.login.LoginActivity
import com.bangkitc23ps404.huze.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val registerViewModel: RegisterViewModel by viewModels {
            factory
        }
        setContentView(binding.root)
        binding.apply {
            btnRegister.setOnClickListener {
                val fullname = binding.edRegisterName.text.toString().trim()
                val email = binding.edRegisterEmail.text.toString().trim()
                val password = binding.edRegisterPassword.text.toString().trim()
                registerViewModel.saveUserRegister(email, password, fullname).observe(this@RegisterActivity) {
                    when (it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Success -> {
                            showLoading(false)
                            toLogin()
                        }
                        is Result.Error -> {
                            Toast.makeText(
                                this@RegisterActivity,
                                R.string.register_failed,
                                Toast.LENGTH_SHORT
                            ).show()
                            showLoading(false)
                        }
                    }
                }
            }

            tvLogin.setOnClickListener {
                toLogin()
            }
        }
    }

    private fun toLogin() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}