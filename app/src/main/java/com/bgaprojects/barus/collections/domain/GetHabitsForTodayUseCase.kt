package com.bgaprojects.barus.collections.domain

import com.bgaprojects.barus.collections.model.HabitItem

interface GetHabitsForTodayUseCase {

    suspend operator fun invoke(): List<HabitItem>
}