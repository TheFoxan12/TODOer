package fr.quarantedeuxmulhouse.tunsinge.todoer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData

// Dao correspondant au DAO de Room, necessaire pour l'utilisation de room
// definition des requetes de base insert, update et delete, ainsi qu'une requete
// pour recuperer tous les elements de la db

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