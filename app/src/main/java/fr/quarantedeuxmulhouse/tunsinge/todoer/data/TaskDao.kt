package fr.quarantedeuxmulhouse.tunsinge.todoer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY initialDate ASC")
    fun getAllTasks(): LiveData<List<TaskData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskData)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTask(task: TaskData)

    @Delete
    suspend fun deleteTask(task: TaskData)
}