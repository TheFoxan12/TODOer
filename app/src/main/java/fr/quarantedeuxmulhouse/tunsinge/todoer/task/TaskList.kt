package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData

@Composable
fun TaskList(
    list: List<TaskData>?,
    onCheckedTask: (TaskData, Boolean) -> Unit,
    onNameChange: (TaskData, String) -> Unit,
    onDateChange: (TaskData, Long?) -> Unit,
    onCloseTask: (TaskData) -> Unit,
    modifier: Modifier = Modifier
) {
    if (!list.isNullOrEmpty()) {
        LazyColumn(
            modifier = modifier
        ) {
            items(
                items = list,
                key = { task: TaskData -> task.id }
            ) { task: TaskData ->
                Task(
                    name = task.name,
                    checked = task.state,
                    date = task.date,
                    onStateChange = { state ->
                        onCheckedTask(task, state)
                    },
                    onNameChange = { name ->
                        onNameChange(task, name)
                    },
                    onDateChange = { date ->
                        onDateChange(task, date)
                    },
                    onClose = { onCloseTask(task) }
                )
            }
        }
    }
    else Text("Il n'y a pas encore de tâches à afficher")
}
