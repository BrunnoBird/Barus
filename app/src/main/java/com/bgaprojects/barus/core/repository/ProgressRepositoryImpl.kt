package com.bgaprojects.barus.core.repository

import com.bgaprojects.barus.core.model.ProgressDomain
import java.util.*

object ProgressRepositoryImpl : ProgressRepository {

    private val progressListCache: MutableList<ProgressDomain> = mutableListOf()

    override suspend fun fetch(habitId: String, completedAt: Long): List<ProgressDomain> {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = completedAt
        return progressListCache.filter {
            it.habitId == habitId && it.dayOfWeek == calendar.get(Calendar.DAY_OF_WEEK)
        }
    }

    override suspend fun delete(id: String) {
        progressListCache.removeAll { it.id == id }
    }

    override suspend fun add(habitId: String) {
        val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val progress = ProgressDomain(
            id = UUID.randomUUID().toString(),
            habitId = habitId,
            dayOfWeek = dayOfWeek
        )

        progressListCache.add(progress)
    }
}