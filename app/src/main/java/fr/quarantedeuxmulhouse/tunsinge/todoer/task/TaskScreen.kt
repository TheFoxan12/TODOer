package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskListViewModel

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    taskManager: TaskListViewModel = viewModel()
) {
    val tasks by taskManager.tasks.observeAsState(emptyList())

    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.weight(2f).fillMaxHeight()
        ) {
            TaskList(
                list = tasks,
                onCheckedTask = { task, state ->
                    taskManager.changeState(task, state)
                },
                onNameChange = { task, name ->
                    taskManager.changeName(task, name)
                },
                onCloseTask = { task ->
                    taskManager.removeTask(task)
                },
                onDateChange = { task, date ->
                    taskManager.changeDate(task, date)
                }
            )
        }
        Row(
            modifier = Modifier.padding(
                bottom = 100.dp
            )
        ) {
            Button(
                onClick = { taskManager.addTask(
                    TaskData(
                        initialName = "Nouvelle tâche",
                        initialState = false
                    )
                ) }
            ) {
                Text("Créer une nouvelle tâche")
            }
        }
    }
}
