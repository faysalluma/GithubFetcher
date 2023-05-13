package com.example.testmobile.data.repositories

import android.content.*
import android.util.Log
import com.example.testmobile.data.database.dao.RepositoryDao
import com.example.testmobile.data.datastore.DataStoreManager
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.data.network.SampleApiInterface
import com.example.testmobile.data.network.adapters.asDto
import com.example.testmobile.data.network.bodies.results.Repository
import com.example.testmobile.domain.repositories.ISampleRepository
import com.example.testmobile.utils.Either
import com.example.testmobile.utils.Failure
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("BlockingMethodInNonBlockingContext")
@Singleton
class SampleRepository @Inject constructor(
    private val repositoryDao: RepositoryDao, private val api: SampleApiInterface,
    @ApplicationContext private val context: Context, private val dataStoreManager: DataStoreManager
) : ISampleRepository {
    companion object {
        private val TAG = SampleRepository::class.simpleName
        private const val REQUEST_TIMEOUT = 20000L
    }

    /* State Flow's */
    private val _repositories = MutableStateFlow<Either<Failure, List<RepositoryDTO>>?>(null)
    override val repositories: StateFlow<Either<Failure, List<RepositoryDTO>>?> = _repositories

    /* Functions */
    override suspend fun getAllRepositories(): Either<Failure, Boolean> {
        // Search Online
        try {
            val response = api.getAllRepositories().execute()
            if (response.isSuccessful) {
                val repositoriesDTO = (response.body() as List<Repository>).map {
                    it.asDto()
                }
                _repositories.emit(Either.Right(repositoriesDTO))
                return Either.Right(true)
            } else {
                Log.w(TAG, "server error")
                val error = Failure.NetworkFailure("Server error : HTTP CODE ERROR ${response.code()}")
                _repositories.emit(Either.Left(error))
                return Either.Left(error)
            }
        } catch (e: IOException) {
            Log.w(TAG, "an exception occurred: ${e.stackTraceToString()}")
            val error = Failure.NetworkFailure(e.message ?: "")
            _repositories.emit(Either.Left(error))
            return Either.Left(error)
        }
    }

}