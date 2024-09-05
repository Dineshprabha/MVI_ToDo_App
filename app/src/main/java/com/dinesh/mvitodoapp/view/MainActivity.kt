package com.dinesh.mvitodoapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.dinesh.mvitodoapp.intent.TodoIntent
import com.dinesh.mvitodoapp.model.repository.TodoRepository
import com.dinesh.mvitodoapp.view.components.MainScreen
import com.dinesh.mvitodoapp.view.ui.theme.MVIToDoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: TodoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVIToDoAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val list by repository.getAllTodoList().collectAsState(initial = emptyList())
                    val scope = rememberCoroutineScope()

                    MainScreen(list = list) { intent ->
                        when (intent) {
                            is TodoIntent.Insert -> scope.launch(Dispatchers.IO) {
                                repository.insert(
                                    intent.todo
                                )
                            }

                            is TodoIntent.Update -> scope.launch(Dispatchers.IO) {
                                repository.update(intent.todo)
                            }

                            is TodoIntent.Delete -> scope.launch(Dispatchers.IO) {
                                repository.delete(intent.todo)
                            }
                        }

                    }

                }
            }
        }
    }
}

