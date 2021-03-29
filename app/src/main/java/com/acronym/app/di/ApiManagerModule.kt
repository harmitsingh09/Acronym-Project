package com.acronym.app.di

import com.acronym.app.network.api.ApiInterface
import com.acronym.app.network.retrofitmanager.ApiManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApiManagerModule {
    @Singleton
    @Provides
    fun providesApiManager(apiInterface: ApiInterface): ApiManager {
        return ApiManager(apiInterface)
    }
}