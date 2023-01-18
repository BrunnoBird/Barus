package com.bgaprojects.barus.core.repository

import com.bgaprojects.barus.core.model.HabitDomain
import java.util.*


object HabitRepositoryImpl : HabitRepository {

    private val habitListCache: MutableList<HabitDomain> = mutableListOf()

    override suspend fun fetchAll() = habitListCache

    override suspend fun fetch(dayOfWeek: Int): List<HabitDomain> {
        return habitListCache.filter {
            it.daysOfWeek.contains(dayOfWeek)
        }
    }

    override suspend fun add(title: String, dayOfWeek: List<Int>) {
        val habit = HabitDomain(
            id = UUID.randomUUID().toString(),
            title = title,
            daysOfWeek = dayOfWeek
        )

        habitListCache.add(habit)
    }
}