package com.example.login_signup_kotlin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    // login
    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    fun loginUser(email: String, password: String): User?

    @Query("SELECT * FROM users ORDER BY nom ASC")
    fun getOrderedByUserName(): Flow<List<User>>

//    @Query("""
//    SELECT users.*, villes.nomVille AS villeName
//    FROM users
//    INNER JOIN villes ON users.villeId = villes.villeId
//    WHERE users.userId = :userId
//""")
//    suspend fun getUserWithVilleName(userId: Int): UserWithVille


}