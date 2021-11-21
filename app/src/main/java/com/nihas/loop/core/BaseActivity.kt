package com.nihas.loop.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.nihas.loop.core.feedback.ApplicationToaster
/*
* Created by Nihas - 12/11/2021
* */
abstract class BaseActivity<DB : ViewBinding>(val bindingFactory: (LayoutInflater) -> DB): AppCompatActivity() {

    val binding: DB by lazy {
        bindingFactory(layoutInflater)
    }


    protected lateinit var toaster: ApplicationToaster

    open fun setUpViews() {}
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.postponeEnterTransition(this)
        setContentView(binding.root)
        onInject()
        setUpViews()
        toaster = ApplicationToaster(this@BaseActivity)
    }

    fun addFragmentBase(@IdRes containerViewId: Int,
                         fragment: Fragment,
                         fragmentTag: String,
                         @Nullable backStackStateName: String?,
                        sharedView: View? = null,
                        transitionName: String? = null) {
//        fragmentTag.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);

        var fragmentTransaction = supportFragmentManager
                .beginTransaction()
//                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(containerViewId, fragment, fragmentTag)
                .addSharedElement(sharedView?:binding.root,transitionName?:"")
        if(backStackStateName==null){
            fragmentTransaction.disallowAddToBackStack()
                    .commit()
        }else{
            fragmentTransaction.addToBackStack(backStackStateName)
                    .commit()
        }
    }

    protected fun replaceFragmentBase(@IdRes containerViewId: Int,
                                       fragment: Fragment,
                                       fragmentTag: String,
                                       @Nullable backStackStateName: String?) {
        var fragmentTransaction = supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(containerViewId, fragment, fragmentTag)
        if(backStackStateName==null){
            fragmentTransaction.disallowAddToBackStack()
                    .commit()
        }else{
            fragmentTransaction.addToBackStack(backStackStateName)
                    .commit()
        }
    }

    fun showSnackBar(msg: String) {
        val snackbar = Snackbar
                .make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG)
//                        .setAction("RETRY") { }
        snackbar.show()
    }
}