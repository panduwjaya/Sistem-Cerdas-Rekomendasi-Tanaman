package com.example.sistemcerdasrekomendasitanaman.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.data.database.DetailTanaman
import com.example.sistemcerdasrekomendasitanaman.databinding.ItemFavoriteBinding

class HorizontalRecyclerView(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<HorizontalRecyclerView.MyViewHolder>() {

    private var dataList : List<DetailTanaman> = ArrayList()

    interface OnItemClickListener {
        fun onItemClick(detailTanaman: DetailTanaman)
    }


    fun setData(data: List<DetailTanaman>){
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
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(dataList[position])
        }
    }

    class MyViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailTanaman: DetailTanaman) {
            with(binding) {
                ivRecycleView.setImageResource(detailTanaman.imageSlide)
                tvPlantName.text = detailTanaman.name
            }
        }
    }
}