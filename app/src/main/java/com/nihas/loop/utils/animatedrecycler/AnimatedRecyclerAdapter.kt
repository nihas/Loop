package com.nihas.loop.utils.animatedrecycler

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class AnimatedRecyclerAdapter<T, VH : AnimatedItemHolder>(itemCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(itemCallback) {

    private var recyclerView: AnimatedRecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = (recyclerView as? AnimatedRecyclerView)
    }

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)

        recyclerView?.onAddViewHolder(holder)
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)

        recyclerView?.onRemoveViewHolder(holder)
    }
}