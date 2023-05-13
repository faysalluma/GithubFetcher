package com.example.testmobile.presentation.detail.contributor

import androidx.lifecycle.*
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.di.DispatcherDefault
import com.example.testmobile.domain.usecases.*
import com.example.testmobile.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(
    private val contributorTaskUseCase: GetContributorTaskUseCase,
    private val contributorStateUseCase: GetContributorStateUseCase,
    @DispatcherDefault defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    // Livedatas
    private val _error = MutableLiveData<Failure?>(null)
    val error: LiveData<Failure?> = _error

    // Data Flows
    val contributors = Transformations.map(contributorStateUseCase.observe(viewModelScope.coroutineContext+ defaultDispatcher)){ it?.right()}

    // Process
    fun getRepositoryContributorList() {
        contributorTaskUseCase.execute(viewModelScope, Unit) {
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