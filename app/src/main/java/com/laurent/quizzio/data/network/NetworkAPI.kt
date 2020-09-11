package com.laurent.quizzio.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
object NetworkAPI {

    @Provides
    fun provideAPI() : INetworkAPI {
        return Retrofit.Builder()
            .baseUrl("https://5ef33fdb25da2f0016228cd5.mockapi.io/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(INetworkAPI::class.java)
    }
}