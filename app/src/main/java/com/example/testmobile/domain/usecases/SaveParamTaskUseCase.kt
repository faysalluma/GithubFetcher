package com.example.testmobile.domain.usecases

import com.example.testmobile.domain.repositories.ISampleRepository
import com.example.testmobile.utils.Either
import com.example.testmobile.utils.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveParamTaskUseCase @Inject constructor(private val sampleRepository: ISampleRepository) : TaskUseCase<Boolean, SaveParamTaskUseCase.Fullname>() {
    override suspend fun run(param: Fullname): Either<Failure, Boolean> {
        return sampleRepository.saveFullName(param.reponame, param.username)
    }
    data class Fullname(val reponame: String, val username: String)
}