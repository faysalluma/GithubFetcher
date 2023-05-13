package com.example.testmobile.presentation.detail.branch

import androidx.lifecycle.*
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.di.DispatcherDefault
import com.example.testmobile.domain.usecases.*
import com.example.testmobile.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class BranchViewModel @Inject constructor(
    private val getBranchTaskUseCase: GetBranchTaskUseCase,
    private val getBranchStateUseCase: GetBranchStateUseCase,
    @DispatcherDefault defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    // Livedatas
    private val _error = MutableLiveData<Failure?>(null)
    val error: LiveData<Failure?> = _error

    // Data Flows
    val branches = Transformations.map(getBranchStateUseCase.observe(viewModelScope.coroutineContext+ defaultDispatcher)){ it?.right()}

    // Process
    fun getRepositoryBranchList() {
        getBranchTaskUseCase.execute(viewModelScope, Unit) {
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