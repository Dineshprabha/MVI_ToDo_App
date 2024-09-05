package com.dinesh.mvitodoapp.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey
    val id : Int=0,
    val title: String,
    val isDone : Boolean = false
)
