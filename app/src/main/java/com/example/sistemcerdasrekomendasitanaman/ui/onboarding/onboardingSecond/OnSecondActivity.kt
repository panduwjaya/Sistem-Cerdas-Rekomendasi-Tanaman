package com.example.sistemcerdasrekomendasitanaman.ui.onboarding.onboardingSecond

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sistemcerdasrekomendasitanaman.MainActivity
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityOnboardingSecondBinding
import com.example.sistemcerdasrekomendasitanaman.ui.onboarding.onboardingThird.OnThirdActivity

class OnSecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imgArrow.setOnClickListener {
            val intent = Intent(this, OnThirdActivity::class.java)
            startActivity(intent)
        }
    }
}