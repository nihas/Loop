package com.nihas.loop.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import coil.load
import com.nihas.loop.core.BaseFragment
import com.nihas.loop.R
import com.nihas.loop.databinding.DetailFragmentBinding

class DetailFragment: BaseFragment<DetailFragmentBinding>(DetailFragmentBinding::inflate) {
    var titleText = ""
    var subTitleText = ""

    companion object{
        fun newInstance(title: String?, subTitle: String?, imageUrl: String?): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString("title",title)
            args.putString("subtitle",subTitle)
            args.putString("imageurl",imageUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun setUpViews() {
        super.setUpViews()

        postponeEnterTransition()

        titleText = arguments?.getString("title").toString()
        subTitleText = arguments?.getString("subtitle").toString()

        binding.bgImage.load(arguments?.getString("imageurl"))

        binding.bgImage.doOnPreDraw {
            startPostponedEnterTransition()
            sharedElementEnterTransition = TransitionInflater.from(requireContext())
                .inflateTransition(R.transition.shared_image)
        }

        binding.recyclerViewDetail.adapter = RecyclerDetailAdapter()
    }

    inner class RecyclerDetailAdapter: RecyclerView.Adapter<RecyclerDetailAdapter.ViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerDetailAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_detail_item, parent, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerDetailAdapter.ViewHolder, position: Int) {
            holder.titleMain.text = titleText
            holder.subTitle.text = subTitleText
        }

        override fun getItemCount(): Int {
            return 1
        }

        inner class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
            val subTitle: TextView = itemView.findViewById(R.id.titleMainText)
            val titleMain: TextView = itemView.findViewById(R.id.subtitleTimeText)
        }

    }
}