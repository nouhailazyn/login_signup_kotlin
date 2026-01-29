package com.example.login_signup_kotlin.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    foreignKeys = [
        ForeignKey(
            entity = Ville::class,
            parentColumns = ["villeId"],
            childColumns = ["villeId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("villeId")]
)
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val nom: String,
    val prenom: String,
    val telephone: String,
    val email: String,
    val password: String,
    val villeId: Int?
)
