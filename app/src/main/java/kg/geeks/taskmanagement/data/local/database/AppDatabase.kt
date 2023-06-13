package kg.geeks.taskmanagement.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import kg.geeks.taskmanagement.data.model.task.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): TaskDao
}