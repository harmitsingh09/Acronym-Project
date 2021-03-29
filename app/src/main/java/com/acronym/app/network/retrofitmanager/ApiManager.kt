package com.acronym.app.network.retrofitmanager

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import com.acronym.app.livedata.EventObject
import com.acronym.app.network.api.ApiInterface
import retrofit2.Response
import com.acronym.app.model.AcronymResponse
import javax.inject.Inject

open class ApiManager @Inject constructor(private val apiInterface: ApiInterface) {
    private var apiResponseEvent: MediatorLiveData<EventObject> = MediatorLiveData()
    private val TAG: String = ApiManager::class.java.simpleName

    /**
     * This implementation create retrofit client for API Server. Its enable self signed certificate of HTTPS server.
     */


    fun getApiResponseEvent(): MediatorLiveData<EventObject>? {
        return apiResponseEvent
    }

    private fun updateAPIResponseEvent(eventObject: EventObject) {
        Log.d(TAG, "updateAPIResponseEvent")
        apiResponseEvent!!.setValue(eventObject)

    }

    suspend fun getAbbreviation(shortName: String) :Response<List<AcronymResponse>?>
    {
        return apiInterface.getAbbreviation(shortName)
    }

}
