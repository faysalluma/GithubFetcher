package com.example.testmobile.presentation.home

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import com.example.testmobile.di.DispatcherDefault
import com.example.testmobile.domain.usecases.GetRepositoryStateUseCase
import com.example.testmobile.domain.usecases.GetRepositoryTaskUseCase
import com.example.testmobile.utils.Failure
import com.example.testmobile.utils.SingleLiveEvent
import com.example.testmobile.utils.right
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val getRepositoryTaskUseCase: GetRepositoryTaskUseCase,
    private val getRepositoryStateUseCase: GetRepositoryStateUseCase,
    @DispatcherDefault defaultDispatcher: CoroutineDispatcher
        ) : ViewModel() {

    // Livedatas
    private val _error = MutableLiveData<Failure?>(null)
    val error: LiveData<Failure?> = _error

    // Data Flows
    val repositories = Transformations.map(getRepositoryStateUseCase.observe(viewModelScope.coroutineContext+ defaultDispatcher)){ it?.right()}

    // Process
    fun getRepositoryList() {
        getRepositoryTaskUseCase.execute(viewModelScope, Unit) {
            it.apply(::handleFailure, ::handleSampleRetrieved)
        }
    }

    private fun handleSampleRetrieved(retrieved: Boolean) {
        // nothing to do
    }

    private fun handleFailure(failure: Failure?) {
        _error.postValue(failure)
    }
}