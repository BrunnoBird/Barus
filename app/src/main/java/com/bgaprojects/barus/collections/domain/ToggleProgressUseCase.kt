package com.bgaprojects.barus.collections.domain

interface ToggleProgressUseCase {

    suspend operator fun invoke(habitId: String)
}