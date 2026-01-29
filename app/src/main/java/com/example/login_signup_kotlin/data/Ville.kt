package com.example.login_signup_kotlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "villes")
data class Ville(
    @PrimaryKey(autoGenerate = true) val villeId: Int = 0,
    val nomVille: String
)
