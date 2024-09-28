package fr.quarantedeuxmulhouse.tunsinge.todoer.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY id ASC")
    fun getAllTasks(): Flow<List<TaskData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskData)

    @Update
    suspend fun updateTask(task: TaskData)

    @Delete
    suspend fun deleteTask(task: TaskData)
}