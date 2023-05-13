package com.example.testmobile.domain.usecases

import com.example.testmobile.data.dto.ContributorDTO
import com.example.testmobile.domain.repositories.ISampleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetContributorStateUseCase @Inject constructor(private val sampleRepository: ISampleRepository) : StateUseCase<List<ContributorDTO>>() {
    override fun provideState() = sampleRepository.contributors
}