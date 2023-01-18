package com.bgaprojects.barus.collections.domain

import com.bgaprojects.barus.core.repository.ProgressRepository
import java.util.Calendar

class ToggleProgressUseCaseImpl(
    private val progressRepository: ProgressRepository
): ToggleProgressUseCase {
    override suspend fun invoke(habitId: String) {
        val today = Calendar.getInstance()
        val progress = progressRepository.fetch(habitId = habitId, today.timeInMillis)
        if(progress.isNotEmpty()) {
            progressRepository.delete(progress.first().id)
        } else {
            progressRepository.add(habitId = habitId)
        }
    }
}