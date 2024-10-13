package com.example.sistemcerdasrekomendasitanaman.ui.primary.detail_tanaman

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.data.database.DetailTanaman
import com.example.sistemcerdasrekomendasitanaman.data.database.DummyData
import com.example.sistemcerdasrekomendasitanaman.data.database.Favorite
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityDetailTanamanBinding
import com.example.sistemcerdasrekomendasitanaman.ui.adapter.HorizontalRecyclerView
import com.example.sistemcerdasrekomendasitanaman.ui.primary.favorite.FavoriteViewModel
import com.example.sistemcerdasrekomendasitanaman.ui.primary.scan.ScanFragment
import kotlinx.coroutines.launch

class DetailTanamanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamanBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorizontalRecyclerView
    private lateinit var favoriteViewModel: FavoriteViewModel
    private var isFavorite: Boolean = false
    private var plantDetail: DetailTanaman? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTanamanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        plantDetail = intent.getParcelableExtra("plantDetail")
        plantDetail?.let { setupDetailPage(it) } ?: showErrorAndFinish()

        binding.btnBack.setOnClickListener { finish() }

        setupRecommendationRecyclerView()
        setupScanButton()
        setupFavoriteButton()

    }

    private fun setupDetailPage(plant: DetailTanaman) {
        binding.apply {
            ivDetailTanaman.setImageResource(plant.imageSlide)
            plantName.text = plant.name
            tvPlantName2.text = plant.synonim
            txtTemp.text = plant.temperature
            tvDetailDescription.text = plant.description
        }
        checkIfFavorite(plant.id)
    }

    private fun showErrorAndFinish() {
        Toast.makeText(this, "Detail tanaman tidak ditemukan", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun setupRecommendationRecyclerView() {
        recyclerView = binding.rvRekomendasi
        adapter = HorizontalRecyclerView(object : HorizontalRecyclerView.OnItemClickListener {
            override fun onItemClick(detailTanaman: DetailTanaman) {
                val intent = Intent(this@DetailTanamanActivity, DetailTanamanActivity::class.java)
                intent.putExtra("plantDetail", detailTanaman)
                startActivity(intent)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
        adapter.setData(DummyData.detailTanaman.shuffled())
    }

    private fun setupScanButton() {
        binding.btnScan.setOnClickListener {
            val scanFragment = ScanFragment()

            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, scanFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupFavoriteButton() {
        binding.btnFavorite.setOnClickListener {
            plantDetail?.let { plant ->
                if (isFavorite) removeFromFavorite(plant) else addToFavorite(plant)
                isFavorite = !isFavorite
                updateFavoriteButton()
            }
        }
    }

    private fun checkIfFavorite(plantId: Int) {
        lifecycleScope.launch {
            isFavorite = favoriteViewModel.isFavorite(plantId)
            updateFavoriteButton()
        }
    }

    private fun addToFavorite(plant: DetailTanaman) {
        val favoritePlant = Favorite(plant.id, plant.name, plant.imageSlide)
        favoriteViewModel.addFavorite(favoritePlant)
        Toast.makeText(this, "${plant.name} ditambahkan ke favorit", Toast.LENGTH_SHORT).show()
    }

    private fun removeFromFavorite(plant: DetailTanaman) {
        val favoritePlant = Favorite(plant.id, plant.name, plant.imageSlide)
        favoriteViewModel.removeFavorite(favoritePlant)
        Toast.makeText(this, "${plant.name} dihapus dari favorit", Toast.LENGTH_SHORT).show()
    }

    private fun updateFavoriteButton() {
        val icon = if (isFavorite) R.drawable.ic_btn_fav_fill else R.drawable.ic_btn_fav
        binding.icBtnFavorite.setImageResource(icon)
    }
}