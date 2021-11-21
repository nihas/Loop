package com.nihas.loop.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.textview.MaterialTextView
import coil.load
import com.nihas.loop.R
import com.nihas.loop.data.CardItem


class ViewPagerAdapter: PagerAdapter(),ImageAdapter {
    var mData: ArrayList<CardItem> = ArrayList()
    var mViews: ArrayList<AppCompatImageView?> = ArrayList()
    var onItemClick: ((CardItem, Int) -> Unit)? = null

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
        var view = LayoutInflater.from(container.context).inflate(R.layout.viewpager_adapter_item,container,false)
        container.addView(view)
        bind(mData[position],view)
        mViews.add(view.findViewById(R.id.bgImage))
        view.setOnClickListener {
            onItemClick?.invoke(mData[position],position)
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        mViews.add(position,null)
        container.removeView(`object` as View)
    }

    private fun bind(cardItem: CardItem, view: View?) {
        view?.findViewById<MaterialTextView>(R.id.subtitleTimeText)?.text = cardItem.title
        view?.findViewById<MaterialTextView>(R.id.titleMainText)?.text = cardItem.subTitle
        view?.findViewById<AppCompatImageView>(R.id.bgImage)?.load(cardItem.imageUrl)
    }

    override fun getImageViewAt(position: Int): AppCompatImageView? {
        return mViews.get(position)
    }

}


interface ImageAdapter{
    fun getImageViewAt(position: Int): AppCompatImageView?
}
