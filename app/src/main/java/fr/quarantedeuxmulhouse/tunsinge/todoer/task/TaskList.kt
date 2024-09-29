package fr.quarantedeuxmulhouse.tunsinge.todoer.task

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData


// affichage de la liste des taches

@Composable
fun TaskList(
    list: List<TaskData>?,
    onCheckedTask: (TaskData, Boolean) -> Unit,
    onNameChange: (TaskData, String) -> Unit,
    onDateChange: (TaskData, Long?) -> Unit,
    onCloseTask: (TaskData) -> Unit,
    modifier: Modifier = Modifier
) {
    // si la liste des taches passee en parametre n'est pas vide on affiche la liste
    if (!list.isNullOrEmpty()) {

        // utilisation d'une lazy column pour la prise en charge du defilement et de la population
        // progressive de la liste
        LazyColumn(
            modifier = modifier
        ) {
            // population de la lazycolumn avec items
            items(
                items = list,
                key = { task: TaskData -> task.id }
            ) { task: TaskData ->

                // pour chaque tache de la liste on cree un composable task pour l'affichage
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
                    onClose = { onCloseTask(task) },
                    modifier = modifier
                )
            }
        }
    }
    // sinon on affiche un texte notifiant de l'absence de taches a afficher
    else Text("Il n'y a pas encore de tâches à afficher")
}
