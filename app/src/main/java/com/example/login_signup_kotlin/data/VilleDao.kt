package com.example.login_signup_kotlin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface VilleDao {
    @Upsert
    suspend fun upsertVille(ville: Ville)

    @Delete
    suspend fun deleteVille(ville: Ville)

    @Query("""SELECT *FROM villes ORDER BY nomVille ASC""")
    fun getAllVilles(): Flow<List<Ville>>

    @Insert
    suspend fun insertAll(ville: List<Ville>)
}