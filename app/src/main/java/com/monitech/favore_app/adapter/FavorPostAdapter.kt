package com.monitech.favore_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monitech.favore_app.R
import com.monitech.favore_app.holder.FavorPostViewHolder
import com.monitech.favore_app.models.Post

class FavorPostAdapter(val listPost: List<Post>): RecyclerView.Adapter<FavorPostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavorPostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavorPostViewHolder(layoutInflater.inflate(R.layout.favor_post_card, parent, false))
    }
    override fun getItemCount(): Int = listPost.size
    override fun onBindViewHolder(holder: FavorPostViewHolder, position: Int) {
        val item = listPost[position]
        holder.render(item)
    }
}