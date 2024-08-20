package com.example.sistemcerdasrekomendasitanaman.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistemcerdasrekomendasitanaman.R

class HorizontalRecyclerView: RecyclerView.Adapter<HorizontalRecyclerView.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 25
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}