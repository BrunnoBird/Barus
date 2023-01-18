package com.bgaprojects.barus.core.repository

import com.bgaprojects.barus.core.model.ProgressDomain

object ProgressRepositoryImpl : ProgressRepository {
    override suspend fun fetch(habitId: String, completedAt: Long): List<ProgressDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun add(habitId: String) {
        TODO("Not yet implemented")
    }
}