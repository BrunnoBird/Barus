package com.bgaprojects.barus.core.repository

import com.bgaprojects.barus.core.model.HabitDomain
import java.time.DayOfWeek

interface HabitRepository {

    suspend fun fetch(dayOfWeek: Int): List<HabitDomain>

    suspend fun add(title: String, dayOfWeek: List<Int>)
}