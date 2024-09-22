package com.example.sistemcerdasrekomendasitanaman.ui.primary.detail_tanaman

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val plantDetail = intent.getParcelableExtra<DetailTanaman>("plantDetail")

        if (plantDetail != null) {
            binding.ivDetailTanaman.setImageResource(plantDetail.imageSlide)
            binding.plantName.text = plantDetail.name
            binding.tvPlantName2.text = plantDetail.synonim
            binding.txtTemp.text = plantDetail.temperature
            binding.tvDetailDescription.text = plantDetail.description
        } else {
            Toast.makeText(this, "Detail tanaman tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

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

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}