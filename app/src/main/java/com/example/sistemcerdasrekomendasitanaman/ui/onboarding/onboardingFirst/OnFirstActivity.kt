package com.example.sistemcerdasrekomendasitanaman.ui.onboarding.onboardingFirst

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityLoginBinding
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityOnboardingFirstBinding
import com.example.sistemcerdasrekomendasitanaman.ui.auth.forgotpassword.ResetPasswordActivity
import com.example.sistemcerdasrekomendasitanaman.ui.onboarding.onboardingSecond.OnSecondActivity

class OnFirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imgArrow.setOnClickListener {
            val intent = Intent(this, OnSecondActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}