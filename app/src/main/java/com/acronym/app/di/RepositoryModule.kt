package com.acronym.app.di

import com.acronym.app.network.retrofitmanager.ApiManager
import com.acronym.app.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDataRepository(apiManager: ApiManager): DataRepository {
        return DataRepository(apiManager)
    }


}