package com.bgaprojects.barus.core.repository

import com.bgaprojects.barus.core.database.AppDatabase
import com.bgaprojects.barus.core.database.entity.Habit
import com.bgaprojects.barus.core.model.HabitDomain
import java.util.*


class HabitRepositoryImpl(appDatabase: AppDatabase) : HabitRepository {
    private val dao = appDatabase.habitDao()

    override suspend fun fetch(dayOfWeek: Int): List<HabitDomain> {
        return dao.fetchByDayOfWeek(dayOfWeek).map {
            HabitDomain(
                id = it.uuid, title = it.title, daysOfWeek = it.daysOfWeek
            )
        }
    }

    override suspend fun add(title: String, dayOfWeek: List<Int>) {
        val habit = Habit(
            uuid = UUID.randomUUID().toString(),
            title = title,
            daysOfWeek = dayOfWeek,
        )

        dao.insert(habit = habit)
    }
}