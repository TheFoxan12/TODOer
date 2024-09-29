package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
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

// affichage de la liste des taches et du bouton d'ajout de tache

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

            // on affiche les taches avec le composable tasklist
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
                },
                modifier = modifier
            )
        }

        // bouton qui sert a ajouter une tache a la liste
        Row(
            modifier = modifier
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(5.dp)
        ) {

            // lors d'un clic on utilise le viewmodel pour ajouter une tache a la liste
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
