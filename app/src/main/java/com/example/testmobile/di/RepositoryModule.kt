package com.example.testmobile.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.testmobile.data.repositories.SampleRepository
import com.example.testmobile.domain.repositories.ISampleRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule  {
    @Singleton
    @Binds
    abstract fun bindApplicationsRepository(impl: SampleRepository): ISampleRepository
}