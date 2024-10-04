package com.example.sistemcerdasrekomendasitanaman.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.R
import com.example.sistemcerdasrekomendasitanaman.data.database.Favorite
import com.example.sistemcerdasrekomendasitanaman.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private val favorites: List<Favorite>,
    private val onItemClicked: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val plantImageView: ImageView = itemView.findViewById(R.id.ivRecycleView)
        private val plantNameTextView: TextView = itemView.findViewById(R.id.tv_plant_name)

        fun bind(favorite: Favorite) {
            plantImageView.setImageResource(favorite.imageUri)
            plantNameTextView.text = favorite.name

            itemView.setOnClickListener {
                onItemClicked(favorite)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount(): Int = favorites.size
}