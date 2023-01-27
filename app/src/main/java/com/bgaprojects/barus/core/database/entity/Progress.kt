package com.bgaprojects.barus.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class Progress(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "habitId") val habitId: String,
    @ColumnInfo(name = "completedAt") val completedAt: Long,
)