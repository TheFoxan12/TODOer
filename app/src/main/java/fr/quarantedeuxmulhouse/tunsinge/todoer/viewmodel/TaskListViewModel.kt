package fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskListViewModel(application: Application) : AndroidViewModel(application) {
    private var _indexMax = 0
    private var _tasks = mutableStateListOf<TaskData>()
    val tasks: List<TaskData>
        get() = _tasks

    fun addTask(text: String) {
        _tasks.add(TaskData(_indexMax++, text, false))
    }

    fun removeTask(task: TaskData) {
        if (_tasks.isNotEmpty())
            _tasks.remove(task)
    }

    fun changeState(task: TaskData, state: Boolean) {
        _tasks.find { it.id == task.id }?.let { item ->
            item.state = state
        }
    }

    fun changeName(task: TaskData, name: String) = viewModelScope.launch {
        _tasks.find { it.id == task.id }?.let { item ->
            item.name = name
        }
    }
}
