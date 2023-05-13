package com.example.testmobile.domain.usecases

import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.domain.repositories.ISampleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetRepositoryStateUseCase @Inject constructor(private val sampleRepository: ISampleRepository) : StateUseCase<List<RepositoryDTO>>() {
    override fun provideState() = sampleRepository.repositories
}