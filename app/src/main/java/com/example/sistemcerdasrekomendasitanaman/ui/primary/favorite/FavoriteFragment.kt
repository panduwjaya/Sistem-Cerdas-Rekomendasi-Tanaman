package com.example.sistemcerdasrekomendasitanaman.ui.primary.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.databinding.FragmentFavoriteBinding
import com.example.sistemcerdasrekomendasitanaman.ui.adapter.HorizontalRecyclerView

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding // Assuming you're using View Binding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HorizontalRecyclerView

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
        adapter = HorizontalRecyclerView()

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }
}

