package com.example.testmobile.domain.usecases

import com.example.testmobile.domain.repositories.ISampleRepository
import com.example.testmobile.utils.Either
import com.example.testmobile.utils.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetBranchTaskUseCase @Inject constructor(private val sampleRepository: ISampleRepository) : TaskUseCase<Boolean, Unit>() {
    override suspend fun run(param: Unit): Either<Failure, Boolean> {
        return sampleRepository.getRepositoryBranches()
    }
}