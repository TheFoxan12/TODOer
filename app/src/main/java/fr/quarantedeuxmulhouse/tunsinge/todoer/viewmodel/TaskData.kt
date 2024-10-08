package fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

// entity utilisee pour le viewmodel et aussi par room pour la creation de la table de la
// base de donnee

@Entity(tableName = "tasks")
data class TaskData(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    var initialName: String = "",
    var initialState: Boolean = false,
    var initialDate: Long? = null,
)
{
    var state by mutableStateOf(initialState)
    var name by mutableStateOf(initialName)
    var date by mutableStateOf(initialDate)
}
