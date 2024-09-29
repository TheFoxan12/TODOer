package fr.quarantedeuxmulhouse.tunsinge.todoer

import android.app.Application
import fr.quarantedeuxmulhouse.tunsinge.todoer.data.TaskDatabase
import fr.quarantedeuxmulhouse.tunsinge.todoer.data.TaskRepository


// application qui cree les instances de database et de repository qui seront utilisees par le
// l'application via le viewmodel
class TodoerApplication: Application() {
    private val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}
