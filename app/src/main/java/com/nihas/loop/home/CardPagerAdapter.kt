package com.nihas.loop.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import androidx.cardview.widget.CardView
import coil.load
import com.nihas.loop.R
import com.nihas.loop.data.CardItem


class CardPagerAdapter: PagerAdapter(),CardAdapter {
    var mData: ArrayList<CardItem> = ArrayList()
    var mViews: ArrayList<MaterialCardView?> = ArrayList()
    var onItemClick: ((CardItem, Int) -> Unit)? = null

    init {
        mData = ArrayList()
        mViews = ArrayList()
    }

    fun addCardItem(item: CardItem){
        mData.add(item)
    }

    fun addAllData(arr: ArrayList<CardItem>){
        mData.addAll(arr)
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = LayoutInflater.from(container.context).inflate(R.layout.card_adapter,container,false)
        container.addView(view)
        bind(mData[position],view)
        mViews.add(view.findViewById(R.id.materialCardview))
        view.setOnClickListener {
            onItemClick?.invoke(mData[position],position)
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
        mViews.add(position,null)
    }

    private fun bind(cardItem: CardItem, view: View?) {
        view?.findViewById<MaterialTextView>(R.id.titleText)?.text = cardItem.title
        view?.findViewById<MaterialTextView>(R.id.subTitleText)?.text = cardItem.subTitle
        view?.findViewById<AppCompatImageView>(R.id.bgImage)?.load(cardItem.imageUrl)
    }

    override fun getCardViewAt(position: Int): CardView? {
        return mViews.get(position)
    }
}

interface CardAdapter{
    fun getCardViewAt(position: Int): CardView?
    fun getCount(): Int
}