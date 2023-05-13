package com.example.testmobile.domain.repositories

import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.utils.Either
import com.example.testmobile.utils.Failure
import kotlinx.coroutines.flow.StateFlow

interface ISampleRepository {
    val repositories: StateFlow<Either<Failure, List<RepositoryDTO>>?>
    suspend fun getAllRepositories(): Either<Failure, Boolean>
}