package fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import fr.quarantedeuxmulhouse.tunsinge.todoer.data.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    var tasks: LiveData<List<TaskData>> = repository.allTasks

    fun addTask(task: TaskData) = viewModelScope.launch {
        repository.insertTask(task)
    }

    fun removeTask(task: TaskData) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    fun changeState(task: TaskData, state: Boolean) = viewModelScope.launch {
        val updatedTask = task.copy(initialState = state)
        repository.updateTask(updatedTask)
    }

    fun changeName(task: TaskData, name: String) = viewModelScope.launch {
        val updatedTask = task.copy(initialName = name)
        repository.updateTask(updatedTask)
    }
}

class TaskListViewModelFactory(private val repository: TaskRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java))
            return TaskListViewModel(repository) as T

        throw IllegalArgumentException("Unknown class")
    }
}