package com.nihas.loop.home

import android.os.SystemClock
import androidx.core.view.doOnPreDraw
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.transition.MaterialElevationScale
import com.nihas.loop.utils.animatedrecycler.ShadowTransformer
import com.nihas.loop.core.BaseFragment
import com.nihas.loop.MainActivity
import com.nihas.loop.R
import com.nihas.loop.databinding.HomeViewBinding
import com.nihas.loop.viewpager.ViewPagerFragment
import com.google.android.material.transition.MaterialSharedAxis
import com.nihas.loop.MainViewModel
import com.nihas.loop.core.injectViewModel
import com.nihas.loop.core.injectViewModelFactory
import com.nihas.loop.data.AboutData
import com.nihas.loop.data.ServiceData


class HomeFragment: BaseFragment<HomeViewBinding>(HomeViewBinding::inflate) {
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    val aboutAdapter = AboutUsAdapter()
    lateinit var cardPagerAdapter: CardPagerAdapter
    private var mCardShadowTransformer: ShadowTransformer? = null
    var sampleArr = arrayListOf<String>("ONE","TWO","Three","Four","FIVE", "SIX")
    private var previousTime = SystemClock.elapsedRealtime()

    override fun onInject() {
        super.onInject()
        viewModelFactory = activity?.application?.let { injectViewModelFactory(it) }!!
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun setUpViews() {
        super.setUpViews()

        postponeEnterTransition()
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Y,true)
        sharedElementReturnTransition = MaterialElevationScale(true)

        cardPagerAdapter = CardPagerAdapter()
        cardPagerAdapter.addAllData(viewModel.getCardData())

        binding.viewPager.adapter = cardPagerAdapter
        binding.viewPager.offscreenPageLimit = 5
        binding.viewPager.setPageTransformer(false, mCardShadowTransformer)

        cardPagerAdapter.onItemClick = { cardItem, position ->
            (activity as MainActivity).addFragment(
                fragment = ViewPagerFragment.newInstance(position),
                backstackName = "HOME",
                sharedView = binding.viewPager.getChildAt(position),
                transitionName = getString(R.string.viewpager_card_transition_name))
        }

        initData(PagerTitle.ABOUT.ordinal)

        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            var SCROLLING_RIGHT = 0
            var SCROLLING_LEFT = 1
            var SCROLLING_UNDETERMINED = 2

            var currentScrollDirection = 2

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (isScrollDirectionUndetermined()){
                    setScrollingDirection(positionOffset)
                }

                if (isScrollingLeft()){
                    // Identify scrolling left
                    if(positionOffsetPixels.toFloat() < (binding.viewPager[0].width/2)){
                        //Second Half
                        binding.headingText.translationX = -(positionOffsetPixels.toFloat())
                        binding.recyclerViewDetails.translationX = -(positionOffsetPixels.toFloat())
                        var percentage = 100 - ((positionOffsetPixels.toFloat()) * 100) / ((binding.viewPager[0].width/2))
                        var value = (percentage * (1.0) / 100.0).toFloat()
                        binding.headingText.alpha = value
                        binding.recyclerViewDetails.alpha = value
                    }else{
                        // First Half
                        binding.headingText.translationX = (binding.viewPager[0].width - positionOffsetPixels.toFloat())
                        binding.recyclerViewDetails.translationX = (binding.viewPager[0].width - positionOffsetPixels.toFloat())
                        var percentage = -(100 - ((positionOffsetPixels.toFloat()) * 100) / ((binding.viewPager[0].width/2)))
                        var value = (percentage * (1.0) / 100.0).toFloat()
                        binding.headingText.alpha = value
                        binding.recyclerViewDetails.alpha = value
                    }
                }
                if (isScrollingRight()){
                    // Identify scrolling right
                    if(positionOffsetPixels.toFloat() > (binding.viewPager[0].width/2)){
                        //Second half scrolling
                        binding.headingText.translationX = (binding.viewPager[0].width - positionOffsetPixels.toFloat())
                        binding.recyclerViewDetails.translationX = (binding.viewPager[0].width - positionOffsetPixels.toFloat())
                        var percentage = -(100 - ((positionOffsetPixels.toFloat()) * 100) / ((binding.viewPager[0].width/2)))
                        var value = (percentage * (1.0) / 100.0).toFloat()
                        binding.headingText.alpha = value
                        binding.recyclerViewDetails.alpha = value
                    }else{
                        //Scrolling started upto first Half
                        binding.headingText.translationX = -(positionOffsetPixels.toFloat())
                        binding.recyclerViewDetails.translationX = -(positionOffsetPixels.toFloat())
                        var percentage = 100 - ((positionOffsetPixels.toFloat()) * 100) / ((binding.viewPager[0].width/2))
                        var value = (percentage * (1.0) / 100.0).toFloat()
                        binding.headingText.alpha = value
                        binding.recyclerViewDetails.alpha = value
                    }
                }
            }


            private fun setScrollingDirection(positionOffset: Float) {
                if (1 - positionOffset >= 0.5) {
                    currentScrollDirection = SCROLLING_RIGHT
                } else if (1 - positionOffset <= 0.5) {
                    currentScrollDirection = SCROLLING_LEFT
                }
            }

            private fun isScrollDirectionUndetermined(): Boolean {
                return currentScrollDirection == SCROLLING_UNDETERMINED
            }

            private fun isScrollingRight(): Boolean {
                return currentScrollDirection == SCROLLING_RIGHT
            }

            private fun isScrollingLeft(): Boolean {
                return currentScrollDirection == SCROLLING_LEFT
            }


            override fun onPageSelected(position: Int) {
                println("onPageSelected $position")
                binding.headingText.text = sampleArr[position]
                if(position%2 == 0) initData(0) else initData(1)
            }

            override fun onPageScrollStateChanged(state: Int) {
                println("onPageScrollStateChanged $state")
                if (state == ViewPager.SCROLL_STATE_IDLE){
                    this.currentScrollDirection = SCROLLING_UNDETERMINED
                }
            }

        })
    }

    private fun initData(type: Int) {
        when(type) {
            PagerTitle.ABOUT.ordinal -> {
                binding.recyclerViewDetails.layoutManager = LinearLayoutManager(activity)
                val data = ArrayList<AboutData>()
                data.addAll(AboutData.getAbout())
                val aboutAdapter = AboutUsAdapter()
                binding.recyclerViewDetails.adapter = aboutAdapter
                aboutAdapter.submitList(data)
            }
            PagerTitle.SERVICES.ordinal -> {
                binding.recyclerViewDetails.layoutManager = LinearLayoutManager(activity)
                val data = ArrayList<ServiceData>()
                data.addAll(ServiceData.getServices())
                val servicesAdapter = ServicesAdapter()
                binding.recyclerViewDetails.adapter = servicesAdapter
                servicesAdapter.submitList(data)
            }
        }

        binding.recyclerViewDetails.doOnPreDraw {  startPostponedEnterTransition() }
    }
}