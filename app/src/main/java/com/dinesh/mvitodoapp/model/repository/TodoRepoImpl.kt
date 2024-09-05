package com.dinesh.mvitodoapp.model.repository

import com.dinesh.mvitodoapp.model.local.Todo
import com.dinesh.mvitodoapp.model.local.TodoDao
import kotlinx.coroutines.flow.Flow

class TodoRepoImpl (
    private val todoDao: TodoDao
): TodoRepository {
    override suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    override suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }

    override suspend fun update(todo: Todo) {
        todoDao.update(todo)
    }

    override fun getAllTodoList(): Flow<List<Todo>> {
        return todoDao.getAllTodoList()
    }
}