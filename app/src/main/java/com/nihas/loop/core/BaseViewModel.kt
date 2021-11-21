package com.nihas.loop.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
/*
* Created by Nihas - 12/11/2021
* */
open class BaseViewModel(application: Application): AndroidViewModel(application) {

    val showLoading = MutableLiveData<Boolean>()
    val errMessage = MutableLiveData<String>()

    var ioCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    //Repository and datasource for network calls
//    var mainDataSource = MainDataSource()
//    val mainRepository = MainRepository(mainDataSource)
}
