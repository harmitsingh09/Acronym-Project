package com.acronym.app.modules.acronym

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acronym.app.base.viewmodel.BaseViewModel
import com.acronym.app.model.AcronymResponse
import com.acronym.app.network.Resource
import com.acronym.app.network.api.ApiInterface
import com.acronym.app.repository.DataRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AcronymViewModel @ViewModelInject constructor(
    @ApplicationContext private val context: Context,
    @NonNull application: Application,
    private val repository: DataRepository,
    private val apiInterface: ApiInterface
) : BaseViewModel(application, repository) {

    val acronameResponse = MutableLiveData<Resource<List<AcronymResponse>?>>()

    private var handler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception -> }

    init {

    }

    fun getAbbreviation(shortName: String) {

        acronameResponse.value = Resource.Loading()

        val job = viewModelScope.launch(Dispatchers.IO + handler)
        {

            val response = repository.getAbbreviation(shortName)

            GlobalScope.launch(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val body = response.body() as List<AcronymResponse>?
                    acronameResponse.value = Resource.Success(body)

                } else {
                    acronameResponse.value = Resource.Failure(Throwable())
                }
            }
        }
        job.invokeOnCompletion {
            if (it != null) {
                acronameResponse.value = Resource.Failure(it)
            }
        }
    }
}