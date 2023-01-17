package com.bgaprojects.barus.collections

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bgaprojects.barus.collections.model.HabitItem

class HabitListViewModel() : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(habitItemList = emptyList()))
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    data class UiState(val habitItemList: List<HabitItem>)
}
