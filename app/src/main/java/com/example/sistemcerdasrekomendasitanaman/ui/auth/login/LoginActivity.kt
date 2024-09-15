package com.example.sistemcerdasrekomendasitanaman.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityLoginBinding
import com.example.sistemcerdasrekomendasitanaman.ui.auth.register.RegisterActivity
import com.example.sistemcerdasrekomendasitanaman.utils.show

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val context = this@LoginActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        navigateToRegister()
        onClickLogin()
    }

    private fun onClickLogin(){
        binding.apply {
            loginButton.setOnClickListener {
                startLoadingState()

                val email = loginEdEmail.text.toString()
                val password = loginEdPassword.text.toString()

                val emailError = if (email.isEmpty()) getString(R.string.email_empty)
                else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) getString(R.string.invalid_email)
                else null

                val passwordError = if (password.isEmpty()) getString(R.string.password_empty)
                else if (password.length < 8) getString(R.string.invalid_password)
                else null

                emailBox.error = emailError
                passwordBox.error = passwordError

            }
            finishLoadingState()
        }
    }

    private fun navigateToRegister(){
        binding.registerHere.setOnClickListener {
            startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    private fun startLoadingState(){
        binding.apply {
            progressBarLogin.show(true)
            loginEdEmail.isEnabled = false
            loginEdPassword.isEnabled = false
            loginButton.isEnabled = false
        }
    }

    private fun finishLoadingState(){
        binding.apply {
            progressBarLogin.show(false)
            loginEdEmail.isEnabled = true
            loginEdPassword.isEnabled = true
            loginButton.isEnabled = true
        }
    }
}