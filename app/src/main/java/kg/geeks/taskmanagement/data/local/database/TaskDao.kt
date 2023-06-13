package kg.geeks.taskmanagement.data.local.database

import androidx.room.*
import kg.geeks.taskmanagement.data.model.task.Task

@Dao
interface TaskDao {

    @Query("SELECT*FROM task ORDER BY id DESC")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}