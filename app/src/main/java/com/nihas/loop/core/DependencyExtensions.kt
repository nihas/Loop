package com.nihas.loop.core

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nihas.loop.core.ViewModelFactory

/**
 * Kotlin extensions for dependency injection
 */

inline fun <reified T : ViewModel> FragmentActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(this, factory).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.injectActivityViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProvider(requireActivity(), factory).get(T::class.java)
}


inline fun Fragment.injectViewModelFactory(application: Application): ViewModelFactory {
    return ViewModelFactory(application)
}

inline fun FragmentActivity.injectViewModelFactory(application: Application): ViewModelFactory {
    return ViewModelFactory(application)
}
