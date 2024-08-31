package com.example.sistemcerdasrekomendasitanaman.ui.primary.detail_tanaman

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.data.database.DetailTanaman
import com.example.sistemcerdasrekomendasitanaman.data.database.DummyData
import com.example.sistemcerdasrekomendasitanaman.databinding.ActivityDetailTanamanBinding
import com.example.sistemcerdasrekomendasitanaman.ui.adapter.HorizontalRecyclerView

class DetailTanamanActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailTanamanBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorizontalRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTanamanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favoriteId = intent.getIntExtra("FAVORITE_ID", -1)
        val favoriteName = intent.getStringExtra("FAVORITE_NAME")

        val dummyDetailTanaman = DetailTanaman(
            id = favoriteId,
            imageSlide = R.drawable.kaktus,
            name = "Aristolochia Acuminata",
            synonim = "Aristolochia indica var. magna, Aristolochia tagala",
            temperature = "20-25°C",
            description = "Lorem ipsum dolor sit amet consectetur. Aliquet fringilla est egestas felis cras ut velit in. Sem nulla nisl iaculis faucibus. Ipsum neque dictum amet vulputate auctor. Non nibh leo lorem tincidunt dictum amet semper. Quis sit eu ultricies mauris lobortis. Senectus mollis nulla duis habitasse enim sed tortor."
        )

        binding.plantName.text = dummyDetailTanaman.name
        binding.tvPlantName2.text = dummyDetailTanaman.synonim
        binding.txtTemp.text = dummyDetailTanaman.temperature
        binding.tvDetailDescription.text = dummyDetailTanaman.description
        binding.ivDetailTanaman.setImageResource(dummyDetailTanaman.imageSlide)

        recyclerView = binding.rvFavorite
        adapter = HorizontalRecyclerView()
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter

        adapter.setData(DummyData.favoriteItems)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

}