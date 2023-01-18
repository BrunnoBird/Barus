package com.bgaprojects.barus.collections.domain

import com.bgaprojects.barus.collections.model.HabitItem
import com.bgaprojects.barus.core.repository.HabitRepository
import com.bgaprojects.barus.core.repository.ProgressRepository
import java.util.*

class GetHabitsForTodayUseCaseImpl(
    private val habitRepository: HabitRepository,
    private val progressRepository: ProgressRepository
) : GetHabitsForTodayUseCase {
    override suspend fun invoke(): List<HabitItem> {
        val today = Calendar.getInstance()
        val dayOfWeek = today.get(Calendar.DAY_OF_WEEK)

        return habitRepository
            .fetch(dayOfWeek)
            .map { habit ->
                val progress = progressRepository.fetch(habit.id, today.timeInMillis)
                val isCompleteToday = progress.isNotEmpty()

                HabitItem(
                    id = habit.id,
                    title = habit.title,
                    isCompleted = isCompleteToday
                )
            }
    }
}