package com.nihas.loop

import android.app.Application
import com.nihas.loop.core.BaseViewModel
import com.nihas.loop.data.CardItem
import java.util.ArrayList

class MainViewModel(application: Application): BaseViewModel(application) {

    fun getCardData(): ArrayList<CardItem> {
        return CardItem.getCardData()
    }

}