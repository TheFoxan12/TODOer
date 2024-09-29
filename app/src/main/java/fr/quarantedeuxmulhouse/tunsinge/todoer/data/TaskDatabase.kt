package fr.quarantedeuxmulhouse.tunsinge.todoer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.quarantedeuxmulhouse.tunsinge.todoer.viewmodel.TaskData

// class database necessaire pour l'utilisation de Room
// permet de recuperer l'instance de la db si elle existe, et de la creer sinon

@Database(entities = [TaskData::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var Instance: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build()
                Instance = instance
                instance
            }
        }
    }
}