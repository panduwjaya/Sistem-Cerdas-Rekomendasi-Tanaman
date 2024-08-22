package com.example.sistemcerdasrekomendasitanaman.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.data.database.Favorite
import com.example.sistemcerdasrekomendasitanaman.databinding.ItemFavoriteBinding

class HorizontalRecyclerView: RecyclerView.Adapter<HorizontalRecyclerView.MyViewHolder>() {

    private var dataList : List<Favorite> = ArrayList()

    fun setData(data: List<Favorite>){
        this.dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemFavoriteBinding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemFavoriteBinding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    class MyViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite) {
            with(binding) {
                tvPlantName.text = favorite.name
            }
        }
    }
}