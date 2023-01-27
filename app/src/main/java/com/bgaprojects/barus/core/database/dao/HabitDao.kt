package com.bgaprojects.barus.core.database.dao

import androidx.room.Query
import com.bgaprojects.barus.core.database.entity.Habit

interface HabitDao {

    /**
     * Query: "SELECT * FROM habit WHERE daysOfWeek LIKE '%'||:dayOfWeek||'%'"
    This SQL query selects all columns (indicated by the "*") from the table "habit"
    where the value in the column "daysOfWeek" contains the value of the parameter "dayOfWeek".
    The "||" symbol is used to concatenate the strings, so the final query searches for the value of "dayOfWeek" surrounded by the wildcard characters "%", which allows for a match anywhere within the string, rather than only at the beginning or end.
     * */
    @Query("SELECT * FROM habit WHERE daysOfWeek LIKE '%'||:dayOfWeek||'%'")
    suspend fun fetchByDayOfWeek(dayOfWeek: Int): List<Habit>

    suspend fun insert(habit: Habit)
}