package com.nihas.loop.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

/*
* Created by Nihas - 12/11/2021
* */
abstract class BaseFragment<DB : ViewBinding>(val bindingFactory: (LayoutInflater, ViewGroup?, Boolean) -> DB): Fragment(){

    lateinit var binding: DB

    open fun setUpViews() {}
    open fun onInject() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = bindingFactory.invoke(inflater, container, false)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInject()
        setUpViews()
    }

    fun showSnackBar(msg: String) {

        val snackbar = Snackbar
                .make(binding.root, msg, Snackbar.LENGTH_LONG)
//                        .setAction("RETRY") { }
        snackbar.show()
    }
}