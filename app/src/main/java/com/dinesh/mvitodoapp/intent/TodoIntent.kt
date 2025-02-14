package com.dinesh.mvitodoapp.intent

import com.dinesh.mvitodoapp.model.local.Todo

sealed class TodoIntent {
    data class Insert(val todo: Todo) : TodoIntent()
    data class Update(val todo: Todo) : TodoIntent()
    data class Delete(val todo: Todo) : TodoIntent()
}