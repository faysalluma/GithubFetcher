package com.example.testmobile.presentation.detail

import androidx.lifecycle.*
import com.example.testmobile.data.dto.RepositoryDTO
import com.example.testmobile.di.DispatcherDefault
import com.example.testmobile.domain.usecases.*
import com.example.testmobile.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @DispatcherDefault defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

}