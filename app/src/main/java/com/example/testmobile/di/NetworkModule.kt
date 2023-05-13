package com.example.testmobile.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.example.testmobile.data.network.ApiClient
import com.example.testmobile.data.network.SampleApiInterface
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule  {
    @Singleton
    @Provides
    fun provideSampleApiInterface(@ApplicationContext context: Context) = ApiClient.buildClient().create(SampleApiInterface::class.java)
}