package com.example.sistemcerdasrekomendasitanaman.ui.primary.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sistemcerdasrekomendasitanaman.data.database.DummyData
import com.example.sistemcerdasrekomendasitanaman.databinding.FragmentFavoriteBinding
import com.example.sistemcerdasrekomendasitanaman.ui.adapter.FavoriteAdapter
import com.example.sistemcerdasrekomendasitanaman.ui.primary.detail_tanaman.DetailTanamanActivity

class FavoriteFragment : Fragment() {
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        setupRecyclerView()

        favoriteViewModel.allFavorites.observe(viewLifecycleOwner) { favorites ->
            if (favorites.isNullOrEmpty()) {
                binding.textFavorite.visibility = View.VISIBLE
            } else {
                binding.textFavorite.visibility = View.GONE
            }

            favoriteAdapter = FavoriteAdapter(favorites) { selectedFavorite ->
                val detailIntent = Intent(requireContext(), DetailTanamanActivity::class.java)
                detailIntent.putExtra("plantDetail", DummyData.getPlantById(selectedFavorite.id))
                startActivity(detailIntent)
            }
            binding.rvFavorite.adapter = favoriteAdapter
        }
    }

    private fun setupRecyclerView() {
        binding.rvFavorite.layoutManager = GridLayoutManager(requireContext(), 3)
    }
}

