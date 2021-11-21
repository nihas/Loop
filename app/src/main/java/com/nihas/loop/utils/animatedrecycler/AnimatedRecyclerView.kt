package com.nihas.loop.utils.animatedrecycler

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nihas.loop.R

class AnimatedRecyclerView @JvmOverloads constructor(
    context: Context,
    var attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private var startAnimationMargin: Float = 0.0f

    init {
        rescaleMargin(attrs)
    }

    fun rescaleMargin(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AnimatedRecyclerView,
            0,
            0
        )
            .apply {
                try {
                    startAnimationMargin =
                        getDimension(R.styleable.AnimatedRecyclerView_startAnimationOffset, 100f)
                } finally {
                    recycle()
                }
            }
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        topMarginY = startAnimationMargin
        bottomMarginY = height - startAnimationMargin
    }

    private var topMarginY: Float = startAnimationMargin
    private var bottomMarginY: Float = height - startAnimationMargin

    private val itemHolders: MutableList<AnimatedItemHolder> = mutableListOf()

    fun onAddViewHolder(animatedItemHolder: AnimatedItemHolder) {
        itemHolders.add(animatedItemHolder)
    }

    fun onRemoveViewHolder(animatedItemHolder: AnimatedItemHolder) {
        itemHolders.remove(animatedItemHolder)
    }

    fun clearItemsWithAnimation(swipeDirection: String){
        itemHolders.forEach {
            it.onExitToLeft(swipeDirection)
        }
    }

    fun enterItemsWithAnimation(swipeDirection: String) {
        itemHolders.forEach {
            it.onEnterFromRight(swipeDirection)
        }
    }

    override fun onScrolled(dx: Int, dy: Int) {
        super.onScrolled(dx, dy)
        rescaleMargin(attrs)

        itemHolders.forEach {
            val itemView = it.itemView
            when {
                itemView.didEnterFromTop(dy) -> it.onEnterFromTop()
                itemView.didEnterFromBottom(dy) -> it.onEnterFromBottom()
                itemView.didExitToTop(dy) -> it.onExitToTop()
                itemView.didExitToBottom(dy) -> it.onExitToBottom()
            }
        }
    }

    private fun View.didEnterFromTop(dy: Int): Boolean =
        bottom >= topMarginY && bottom + dy < topMarginY

    private fun View.didEnterFromBottom(dy: Int): Boolean =
        top <= bottomMarginY && top + dy > bottomMarginY

    private fun View.didExitToTop(dy: Int): Boolean =
        bottom <= topMarginY && bottom + dy > topMarginY

    private fun View.didExitToBottom(dy: Int): Boolean =
        top >= bottomMarginY && top + dy < bottomMarginY
}