package com.bgaprojects.barus.core.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.bgaprojects.barus.core.database.entity.Progress

interface ProgressDao {

    /**
     * SQL Explanation:
    """
    SELECT * FROM
    WHERE habitId like :habitId
    AND DATE(completedAt/1000, 'unixepoch') = DATE(:completedAt/1000, 'unixepoch')
    """
     *
     * This SQL query selects all columns ("*") from a table,
     * where the value in the column "habitId" is like the value of the parameter "habitId"
     * and the value in the column "completedAt" is equal to the value of the parameter "completedAt"
     * when both values are converted to dates using the "DATE()" function.
     * The "unixepoch" format is used to interpret the input as a Unix timestamp (number of seconds since January 1st 1970).
     * The value of both the completedAt and habitId are being passed as a parameter in the query.
     * The "like" operator is used to find a match anywhere within the string.
     * */
    @Query(
        """
        SELECT * FROM
        WHERE habitId like :habitId
        AND DATE(completedAt/1000, 'unixepoch') = DATE(:completedAt/1000, 'unixepoch')
    """
    )
    suspend fun fetchProgressByHabit(habitId: String, completedAt: Long): List<Progress>

    @Insert
    suspend fun insert(progress: Progress)

    @Query(
        """
        DELETE FROM progress WHERE uuid = :progressId
    """
    )
    suspend fun delete(progressId: String)
}