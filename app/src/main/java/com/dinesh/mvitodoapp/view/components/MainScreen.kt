package com.dinesh.mvitodoapp.view.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dinesh.mvitodoapp.intent.TodoIntent
import com.dinesh.mvitodoapp.model.local.Todo

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(list: List<Todo>, onIntent: (TodoIntent) -> Unit) {

    val title = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Todo App") }) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            if (list.isEmpty()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Nothing Found")
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(list) {
                        val isChecked = remember {
                            mutableStateOf(it.isDone)
                        }

                        Column(
                            modifier = Modifier
                                .combinedClickable(
                                    enabled = true,
                                    onClick = {},
                                    onLongClick = {
                                        onIntent.invoke(TodoIntent.Delete(it))
                                    })
                                .fillMaxWidth(),
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = it.title)
                                Checkbox(checked = isChecked.value, onCheckedChange = { check ->
                                    isChecked.value = check
                                    onIntent.invoke(TodoIntent.Update(it.copy(isDone = check)))
                                })

                            }
                            HorizontalDivider()
                        }
                    }

                }
            }

            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(value = title.value, onValueChange = {
                    title.value = it
                }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onIntent.invoke(
                            TodoIntent.Insert(
                                Todo(title = title.value, isDone = false)
                            )
                        )
                        title.value = ""
                    }) {
                    Text(text = "Save Todo")
                }

            }
        }
    }

}