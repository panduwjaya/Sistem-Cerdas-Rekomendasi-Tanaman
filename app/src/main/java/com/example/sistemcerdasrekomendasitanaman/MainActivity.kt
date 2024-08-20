package com.example.sistemcerdasrekomendasitanaman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityMainBinding
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: ChipNavigationBar = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navView.setOnItemSelectedListener { itemId ->
            when (itemId) {
                R.id.navigation_scan -> {
                    navController.navigate(R.id.navigation_scan)
                }
                R.id.navigation_favorite -> {
                    navController.navigate(R.id.navigation_favorite)
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                }
            }
        }

        navView.post {
            navView.setItemSelected(R.id.navigation_scan, true)
        }
    }
}

