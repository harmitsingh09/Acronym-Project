package com.acronym.app.di


import com.acronym.app.network.api.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun provideUserService(@SnippetApiModule retrofit: Retrofit):  ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }

}