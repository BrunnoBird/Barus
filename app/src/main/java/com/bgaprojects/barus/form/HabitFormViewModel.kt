package com.bgaprojects.barus.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bgaprojects.barus.core.repository.HabitRepository
import kotlinx.coroutines.launch

class HabitFormViewModel(
    private val habitRepository: HabitRepository
) : ViewModel() {

    fun addHabit(title: String, habitDaysSelected: List<Int>) {
        viewModelScope.launch {
            habitRepository.add(title, habitDaysSelected)
        }
    }

    class Factory(
        private val habitRepository: HabitRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HabitFormViewModel(habitRepository) as T
        }
    }
}