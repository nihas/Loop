package com.nihas.loop

import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import com.nihas.loop.core.BaseActivity
import com.nihas.loop.databinding.ActivityMainBinding
import com.nihas.loop.home.HomeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){

    var scope = CoroutineScope(Dispatchers.Default)

    override fun setUpViews() {
        super.setUpViews()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        scope.launch {
            delay(3000)
            scope.launch(Dispatchers.Main) {
                binding.motionMain.transitionToEnd()
            }
        }

        binding.motionMain.addTransitionListener(object: MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(
                motionLayout: MotionLayout?,
                currentId: Int
            ) {
                //Adding fragment after transition completed
                showFragment(HomeFragment())
                //LOOP logo animation start
                timeOutRemoveTimer.start()
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {
            }

        })

        binding.lottieBackButton.setOnClickListener { onBackPressed() }
    }

    fun showFragment(fragment: Fragment, backstackName: String? = null) {
        replaceFragmentBase(binding.containerFragment.id, fragment, fragment.javaClass.simpleName,backstackName)
    }

    fun addFragment(fragment: Fragment, backstackName: String? = null, sharedView: View? = null,transitionName: String? = null){
        addFragmentBase(binding.containerFragment.id, fragment, fragment.javaClass.simpleName,backstackName,sharedView,transitionName)
    }

    val TOTAL_TIME = 1500L
    private var timeOutRemoveTimer = object : CountDownTimer(TOTAL_TIME, 10) {
        override fun onFinish() {
            binding.circleProgress.progress = 0.92f
        }

        override fun onTick(millisUntilFinished: Long) {
            if((TOTAL_TIME - millisUntilFinished).toFloat() / TOTAL_TIME < 0.92)
            binding.circleProgress.progress = (TOTAL_TIME - millisUntilFinished).toFloat() / TOTAL_TIME
        }
    }

    fun showBackButton(){
        binding.lottieBackButton.alpha = 1F
        binding.circleProgress.alpha = 0F
        binding.lottieBackButton.isClickable = true
        binding.lottieBackButton.apply {
            setAnimation(R.raw.back_anim)
            repeatCount = 0
            playAnimation()
        }
    }

    fun resetLoopLogo(){
        timeOutRemoveTimer.start()
        binding.circleProgress.alpha = 1F
        binding.lottieBackButton.alpha = 0F
        binding.lottieBackButton.isClickable = false
    }

    override fun onBackPressed() {
        super.onBackPressed()
        var fragment = supportFragmentManager.findFragmentById(binding.containerFragment.id)
        if(fragment is HomeFragment)
            resetLoopLogo()
    }

}