package fr.quarantedeuxmulhouse.tunsinge.todoer.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData

// implementation repository du dao, pour l'utilisation de Room

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<TaskData>> = taskDao.getAllTasks()

    @WorkerThread
    suspend fun insertTask(task: TaskData) {
        taskDao.insertTask(task)
    }

    @WorkerThread
    suspend fun updateTask(task: TaskData) {
        taskDao.updateTask(task)
    }

    @WorkerThread
    suspend fun deleteTask(task: TaskData) {
        taskDao.deleteTask(task)
    }
}