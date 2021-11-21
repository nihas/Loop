package com.nihas.loop.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.nihas.loop.utils.animatedrecycler.AnimatedRecyclerAdapter
import com.nihas.loop.utils.animatedrecycler.PostViewHolder
import com.nihas.loop.R
import com.nihas.loop.data.AboutData

class AboutUsAdapter: AnimatedRecyclerAdapter<AboutData, PostViewHolder>(ItemCallbackImpl()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.aboutus_item, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}


class ItemCallbackImpl<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        oldItem == newItem
}