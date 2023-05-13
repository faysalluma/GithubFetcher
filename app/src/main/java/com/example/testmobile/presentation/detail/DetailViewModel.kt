package com.example.testmobile.presentation.detail

import androidx.lifecycle.*
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.di.DispatcherDefault
import com.example.testmobile.domain.usecases.*
import com.example.testmobile.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val saveParamTaskUseCase: SaveParamTaskUseCase
) : ViewModel() {

    val createViewPager = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun setRefreshTabLayout() {
        loading.value = false   // For close Progress
        createViewPager.postValue(true)  // For ViewPager
    }

    fun saveFullName(reponame: String, username: String) {
        saveParamTaskUseCase.execute(viewModelScope, SaveParamTaskUseCase.Fullname(reponame, username)) {
            it.apply(::handleFailure, ::handleSampleRetrieved)
        }
    }

    private fun handleSampleRetrieved(retrieved: Boolean) {
        // nothing to do
    }

    private fun handleFailure(failure: Failure?) {
        // nothing to do
    }
}