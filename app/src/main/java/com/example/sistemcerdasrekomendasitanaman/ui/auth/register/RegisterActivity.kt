package com.example.sistemcerdasrekomendasitanaman.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityRegisterBinding
import com.example.sistemcerdasrekomendasitanaman.ui.auth.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        registAuth()
    }

    private fun registAuth() {
        auth = FirebaseAuth.getInstance()
        binding.registerButton.setOnClickListener {
            val email = binding.registerEdEmail.text.toString()
            val password = binding.registerEdPassword.text.toString()

            if (email.isEmpty()) {
                binding.registerEdEmail.error = "Email Harus Diisi"
                binding.registerEdEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.registerEdEmail.error = "Email Tidak Valid"
                binding.registerEdEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.registerEdPassword.error = "Password Harus Diisi"
                binding.registerEdPassword.requestFocus()
                return@setOnClickListener
            }

            if(password.length < 6){
                binding.registerEdPassword.error = "Password Minimal 6 Karakter"
                binding.registerEdPassword.requestFocus()
                return@setOnClickListener
            }

            registerAccount(email, password)
        }
    }

    private fun registerAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful){
                    Toast.makeText(this,"Register Berhasil",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
    }
}