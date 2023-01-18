package com.bgaprojects.barus.core.repository

import com.bgaprojects.barus.core.model.ProgressDomain

interface ProgressRepository {

    suspend fun fetch(habitId: String, completedAt: Long): List<ProgressDomain>

    suspend fun delete(id: String)

    suspend fun add(habitId: String)
}