package com.example.sistemcerdasrekomendasitanaman.ui.primary.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.data.database.Favorite
import com.example.sistemcerdasrekomendasitanaman.databinding.FragmentFavoriteBinding
import com.example.sistemcerdasrekomendasitanaman.ui.adapter.GridRecyclerView

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding // Assuming you're using View Binding
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

        recyclerView = binding.rvFavorite
        adapter = GridRecyclerView()

        val textViewFavorite = binding.textFavorite
        textViewFavorite.visibility = View.GONE

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.adapter = adapter

        val favoriteList = getFavoriteList() // Replace with your data fetching logic
        adapter.setData(favoriteList)
    }

    private fun getFavoriteList(): List<Favorite> {
        return listOf(Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata"),
            Favorite(1, "Aristolochia Acuminata")
        )
    }
}

