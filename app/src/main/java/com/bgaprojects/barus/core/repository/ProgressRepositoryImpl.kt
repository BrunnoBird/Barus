package com.bgaprojects.barus.core.repository

import com.bgaprojects.barus.core.database.AppDatabase
import com.bgaprojects.barus.core.database.entity.Progress
import com.bgaprojects.barus.core.model.ProgressDomain
import java.util.*

class ProgressRepositoryImpl(appDatabase: AppDatabase) : ProgressRepository {
    private val dao = appDatabase.progressDao()

    override suspend fun fetch(habitId: String, completedAt: Long): List<ProgressDomain> {
        return dao.fetchProgressByHabit(habitId, completedAt).map { progress ->
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = progress.completedAt

            ProgressDomain(
                id = progress.uuid,
                habitId = progress.habitId,
                dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            )
        }
    }

    override suspend fun delete(id: String) {
        dao.delete(progressId = id)
    }

    override suspend fun add(habitId: String) {
        val dayOfWeek = Calendar.getInstance()
        val progress = Progress(
            uuid = UUID.randomUUID().toString(),
            habitId = habitId,
            completedAt = dayOfWeek.timeInMillis //salvamos completo (com milissegundos)
        )
WW
        dao.insert(progress)
    }
}