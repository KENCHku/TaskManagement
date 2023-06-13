package kg.geeks.taskmanagement

import android.app.Application
import androidx.room.Room
import kg.geeks.taskmanagement.data.local.database.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-name"
        )
            .allowMainThreadQueries().build()
    }

    companion object {
        lateinit var db: AppDatabase
    }
}