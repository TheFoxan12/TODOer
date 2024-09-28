package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskListViewModel

@Composable
fun TaskScreen(
    modifier: Modifier = Modifier,
    taskManager: TaskListViewModel = viewModel()
) {
    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.weight(2f).fillMaxHeight()
        ) {
            TaskList(
                list = taskManager.tasks,
                onCheckedTask = { task, state ->
                    taskManager.changeState(task, state)
                },
                onNameChange = { task, name ->
                    taskManager.changeName(task, name)
                },
                onCloseTask = { task ->
                    taskManager.removeTask(task)
                }
            )
        }
        Row(
        ) {
            Button(
                onClick = { taskManager.addTask("nouvelle tâche") }
            ) {
                Text("Créer une nouvelle tâche")
            }
        }
    }
}
