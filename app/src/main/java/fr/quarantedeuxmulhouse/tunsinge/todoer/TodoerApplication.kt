package fr.quarantedeuxmulhouse.tunsinge.todoer

import android.app.Application
import fr.quarantedeuxmulhouse.tunsinge.todoer.data.TaskDatabase
import fr.quarantedeuxmulhouse.tunsinge.todoer.data.TaskRepository

class TodoerApplication: Application() {
    private val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}
