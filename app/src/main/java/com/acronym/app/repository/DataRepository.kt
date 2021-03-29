package com.acronym.app.repository

import androidx.lifecycle.MediatorLiveData
import com.acronym.app.livedata.EventObject
import com.acronym.app.livedata.LiveDataEvent
import com.acronym.app.model.AcronymResponse
import com.acronym.app.network.retrofitmanager.ApiManager
import retrofit2.Response
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val apiManager: ApiManager
) {
    private var mBroadcastEvent: MediatorLiveData<LiveDataEvent<EventObject>> = MediatorLiveData()

    init {
        subscribeToAPIManager();
    }

    fun getBroadCastEvent(): MediatorLiveData<LiveDataEvent<EventObject>> {
        return mBroadcastEvent
    }

    private fun subscribeToAPIManager() {

    }

    suspend fun getAbbreviation(shortName: String): Response<List<AcronymResponse>?> {
        return apiManager.getAbbreviation(shortName)
    }
}

