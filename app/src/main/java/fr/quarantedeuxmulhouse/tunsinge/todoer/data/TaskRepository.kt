package fr.quarantedeuxmulhouse.tunsinge.todoer.data

import androidx.annotation.WorkerThread
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<TaskData>> = taskDao.getAllTasks()

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