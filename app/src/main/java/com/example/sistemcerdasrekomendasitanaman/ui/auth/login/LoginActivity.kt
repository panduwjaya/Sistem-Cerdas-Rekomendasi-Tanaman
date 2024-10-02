package com.example.sistemcerdasrekomendasitanaman.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityLoginBinding
import com.example.sistemcerdasrekomendasitanaman.ui.auth.forgotpassword.ResetPasswordActivity
import com.example.sistemcerdasrekomendasitanaman.ui.auth.register.RegisterActivity
import com.example.sistemcerdasrekomendasitanaman.ui.onboarding.onboardingFirst.OnFirstActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.forgotPassword.setOnClickListener {
            val intent = Intent(this,ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.registerHere.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        loginAuth()
    }

    private fun loginAuth() {
        binding.loginButton.setOnClickListener {
            val email = binding.loginEdEmail.text.toString()
            val password = binding.loginEdPassword.text.toString()

            if (email.isEmpty()) {
                binding.loginEdEmail.error = "Email Harus Diisi"
                binding.loginEdEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.loginEdEmail.error = "Email Tidak Valid"
                binding.loginEdEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.loginEdPassword.error = "Password Harus Diisi"
                binding.loginEdPassword.requestFocus()
                return@setOnClickListener
            }

            if(password.length < 6){
                binding.loginEdPassword.error = "Password Minimal 6 Karakter"
                binding.loginEdPassword.requestFocus()
                return@setOnClickListener
            }

            loginAccount(email, password)
        }
    }

    private fun loginAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this,"Login Berhasil",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, OnFirstActivity::class.java)
                    startActivity(intent)
                    finish()
                } else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}