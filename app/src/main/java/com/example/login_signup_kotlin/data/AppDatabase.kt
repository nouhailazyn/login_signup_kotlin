package com.example.login_signup_kotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class, Article::class, Ville::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun VilleDao(): VilleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        CoroutineScope(Dispatchers.IO).launch {
                            getInstance(context).VilleDao().insertAll(
                                listOf(
                                    Ville(nomVille = "Casablanca"),
                                    Ville(nomVille = "Rabat"),
                                    Ville(nomVille = "Marrakech"),
                                    Ville(nomVille = "Fes"),
                                    Ville(nomVille = "Agadir"),
                                    Ville(nomVille = "Khouribga"),
                                    Ville(nomVille = "Oujda"),
                                    Ville(nomVille = "Ouarzazat"),
                                    Ville(nomVille = "Meknas"),
                                    Ville(nomVille = "Tanger"),
                                )
                            )
                        }
                    }
                })
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}