package fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskData(
    @PrimaryKey
    val id: Int = 0,
    var initialName: String = "",
    var initialState: Boolean = false
)
{
    var state by mutableStateOf(initialState)
    var name by mutableStateOf(initialName)
}
