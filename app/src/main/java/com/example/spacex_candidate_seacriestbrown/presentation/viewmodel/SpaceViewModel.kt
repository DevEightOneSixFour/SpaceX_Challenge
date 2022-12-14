package com.example.spacex_candidate_seacriestbrown.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacex_candidate_seacriestbrown.data.model.local.EntityLaunchData
import com.example.spacex_candidate_seacriestbrown.domain.usecase.LaunchApiUseCase
import com.example.spacex_candidate_seacriestbrown.util.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpaceViewModel @Inject constructor(
    private val useCase: LaunchApiUseCase
): ViewModel() {

    private val _launches = MutableLiveData<List<EntityLaunchData>>()
    val launches: LiveData<List<EntityLaunchData>> get() = _launches

    fun fetchLaunches(context: Context) {
        viewModelScope.launch {
            if (NetworkUtil.isNetworkConnected(context)) {
                _launches.postValue(useCase.fetchLaunches())
            } else {
                _launches.postValue(useCase.fetchFromLocal())
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch { useCase.deleteAll() }
    }
}