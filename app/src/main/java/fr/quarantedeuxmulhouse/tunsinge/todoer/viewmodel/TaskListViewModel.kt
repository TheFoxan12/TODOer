package fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.quarantedeuxmulhouse.tunsinge.todoer.data.TaskRepository
import kotlinx.coroutines.launch

// viewmodel permettant la gestion correcte de la liste des taches

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {

    // on recupere la liste des taches grace a la methode du repository
    var tasks: LiveData<List<TaskData>> = repository.allTasks

    // implementation des methodes de manipulation de la liste de taches, et qui utilisent les
    // methodes du repository

    // ajout d'une tache
    fun addTask(task: TaskData) = viewModelScope.launch {
        repository.insertTask(task)
    }

    // retrait d'une tache
    fun removeTask(task: TaskData) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    // changement de l'etat d'une tache et mise a jour de la tache
    fun changeState(task: TaskData, state: Boolean) = viewModelScope.launch {
        val updatedTask = task.copy(initialState = state)
        repository.updateTask(updatedTask)
    }

    // changement du nom d'une tache et mise a jour de la tache
    fun changeName(task: TaskData, name: String) = viewModelScope.launch {
        val updatedTask = task.copy(initialName = name)
        repository.updateTask(updatedTask)
    }

    // changement de la date deadlin d'une tache et mise a jour de la tache
    fun changeDate(task: TaskData, date: Long?) = viewModelScope.launch {
        val updatedTask = task.copy(initialDate = date)
        repository.updateTask(updatedTask)
    }
}

// factory utilisee pour creer un viewmodel qui prend en argument un repository
class TaskListViewModelFactory(private val repository: TaskRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java))
            return TaskListViewModel(repository) as T

        throw IllegalArgumentException("Unknown class")
    }
}
