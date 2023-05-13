package com.example.testmobile.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.StateFlow
import com.example.testmobile.utils.Either
import com.example.testmobile.utils.Failure
import kotlin.coroutines.CoroutineContext

abstract class StateUseCase<T> {
    protected abstract fun provideState(): StateFlow<Either<Failure, T>?>
    fun observe(context: CoroutineContext): LiveData<Either<Failure, T>?> = provideState().asLiveData(context)
}