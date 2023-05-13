package com.example.testmobile.domain.repositories

import com.example.testmobile.data.dto.ContributorDTO
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.data.dtole.BranchDTO
import com.example.testmobile.utils.Either
import com.example.testmobile.utils.Failure
import kotlinx.coroutines.flow.StateFlow

interface ISampleRepository {
    val repositories: StateFlow<Either<Failure, List<RepositoryDTO>>?>
    val contributors: StateFlow<Either<Failure, List<ContributorDTO>>?>
    val branches: StateFlow<Either<Failure, List<BranchDTO>>?>
    suspend fun getAllRepositories(): Either<Failure, Boolean>
    suspend fun getRepositoryBranches(): Either<Failure, Boolean>
    suspend fun getRepositoryContributors(): Either<Failure, Boolean>
    suspend fun saveFullName(reponame: String, username: String): Either<Failure, Boolean>
}