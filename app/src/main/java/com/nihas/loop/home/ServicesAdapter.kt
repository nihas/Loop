package com.nihas.loop.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nihas.loop.utils.animatedrecycler.AnimatedRecyclerAdapter
import com.nihas.loop.utils.animatedrecycler.PostViewHolder
import com.nihas.loop.R
import com.nihas.loop.data.ServiceData

class ServicesAdapter: AnimatedRecyclerAdapter<ServiceData, PostViewHolder>(ItemCallbackImpl()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.services_item, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}