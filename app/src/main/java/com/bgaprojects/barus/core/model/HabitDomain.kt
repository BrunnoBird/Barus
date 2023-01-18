package com.bgaprojects.barus.core.model

data class HabitDomain(
    val id: String,
    val title: String,
    val daysOfWeek: List<Int>,
)
