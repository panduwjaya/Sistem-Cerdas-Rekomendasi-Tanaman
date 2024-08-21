package com.example.sistemcerdasrekomendasitanaman.ui.primary.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.data.database.DummyData
import com.example.sistemcerdasrekomendasitanaman.data.database.Favorite
import com.example.sistemcerdasrekomendasitanaman.databinding.FragmentFavoriteBinding
import com.example.sistemcerdasrekomendasitanaman.ui.adapter.GridRecyclerView
import com.example.sistemcerdasrekomendasitanaman.ui.primary.detail_tanaman.DetailTanamanActivity

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GridRecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textViewFavorite = binding.textFavorite
        textViewFavorite.visibility = View.GONE

        recyclerView = binding.rvFavorite
        adapter = GridRecyclerView { favorite ->
            val intent = Intent(requireContext(), DetailTanamanActivity::class.java)
            intent.putExtra("FAVORITE_ID", favorite.id)
            intent.putExtra("FAVORITE_NAME", favorite.name)
            startActivity(intent)
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = adapter

        adapter.setData(DummyData.favoriteItems)
    }
}

