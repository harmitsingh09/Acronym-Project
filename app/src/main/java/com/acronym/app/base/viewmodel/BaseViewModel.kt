package com.acronym.app.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.acronym.app.livedata.EventObject
import com.acronym.app.livedata.LiveDataEvent
import com.acronym.app.repository.DataRepository

open class BaseViewModel(application: Application,private val repository: DataRepository) : AndroidViewModel(application) {
     var mBroadcastEvent: LiveData<LiveDataEvent<EventObject>>? = null

    init {
        this.mBroadcastEvent = repository.getBroadCastEvent()
    }

    override fun onCleared() {
        super.onCleared()
    }

}