package com.nihas.loop.viewpager

import android.os.Bundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.doOnPreDraw
import androidx.transition.TransitionInflater
import androidx.viewpager.widget.ViewPager
import com.nihas.loop.utils.animatedrecycler.ShadowTransformer
import com.nihas.loop.core.BaseFragment
import com.nihas.loop.MainActivity
import com.nihas.loop.R
import com.nihas.loop.databinding.ViewpagerViewBinding
import com.nihas.loop.detail.DetailFragment
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import com.nihas.loop.MainViewModel
import com.nihas.loop.core.injectViewModel
import com.nihas.loop.core.injectViewModelFactory


class ViewPagerFragment: BaseFragment<ViewpagerViewBinding>(ViewpagerViewBinding::inflate) {
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    lateinit var cardPagerAdapter: ViewPagerAdapter
    private var mCardShadowTransformer: ShadowTransformer? = null

    companion object{
        fun newInstance(position: Int): ViewPagerFragment {
            val fragment = ViewPagerFragment()
            val args = Bundle()
            args.putInt("position",position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onInject() {
        super.onInject()
        viewModelFactory = activity?.application?.let { injectViewModelFactory(it) }!!
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun setUpViews() {
        super.setUpViews()
        (activity as MainActivity).showBackButton()

        // Postpone enter transitions to allow shared element transitions to run.
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)

        cardPagerAdapter = ViewPagerAdapter()
        cardPagerAdapter.addAllData(viewModel.getCardData())

        binding.viewPager.adapter = cardPagerAdapter
        binding.viewPager.setPageTransformer(false, mCardShadowTransformer)
        binding.viewPager.setPageTransformer(false, mCardShadowTransformer)

        binding.viewPager.currentItem = arguments?.getInt("position") ?: 0
        binding.viewPager.offscreenPageLimit = 5

        binding.viewPager.doOnPreDraw {
            startPostponedEnterTransition()
        }

        cardPagerAdapter.onItemClick = { cardItem, position ->
            (activity as MainActivity).addFragment(
                fragment = DetailFragment.newInstance(cardItem.title,cardItem.subTitle,cardItem.imageUrl),
                backstackName = "VIEWPAGER",
                sharedView = binding.viewPager.getChildAt(position).findViewById(R.id.bgImage),
                transitionName = getString(R.string.image_transition_name)
            )
        }

        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                // Image Transform Animation
                var cardView = binding.viewPager.get(position)
                var bgImage = cardView.findViewById<AppCompatImageView>(R.id.bgImage)
                val scaleDown: Animation = AnimationUtils.loadAnimation(context, R.anim.scale_down)
                bgImage.startAnimation(scaleDown)
            }

            override fun onPageScrollStateChanged(state: Int) {}

        })
    }

}