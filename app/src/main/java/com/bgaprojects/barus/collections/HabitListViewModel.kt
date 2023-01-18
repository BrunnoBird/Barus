package com.bgaprojects.barus.collections

import androidx.lifecycle.*
import com.bgaprojects.barus.collections.domain.GetHabitsForTodayUseCase
import com.bgaprojects.barus.collections.domain.ToggleProgressUseCase
import com.bgaprojects.barus.collections.model.HabitItem
import kotlinx.coroutines.launch

class HabitListViewModel(
    private val getHabitsForTodayUseCase: GetHabitsForTodayUseCase,
    private val toggleProgressUseCase: ToggleProgressUseCase
) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {
        MutableLiveData<UiState>(UiState(habitItemList = emptyList()))
    }

    fun onResume() {
        viewModelScope.launch {
            refreshHabitList()
        }
    }

    fun stateOnceAndStream(): LiveData<UiState> {
        return uiState
    }

    fun toggleHabitCompleted(habitId: String) {
        viewModelScope.launch {
            toggleProgressUseCase(habitId)
            refreshHabitList()
        }
    }

    private suspend fun refreshHabitList(){
        uiState.postValue(UiState(getHabitsForTodayUseCase()))
    }

    data class UiState(val habitItemList: List<HabitItem>)

    class Factory(
        private val toggleProgressUseCase: ToggleProgressUseCase,
        private val getHabitsForTodayUseCase: GetHabitsForTodayUseCase,
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HabitListViewModel(getHabitsForTodayUseCase, toggleProgressUseCase) as T
        }
    }
}
