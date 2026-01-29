package com.example.login_signup_kotlin.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true) val articleId: Int = 0,
    val designation: String,
    val prix: Double,
    val quantite: Int,
    val login: String // cle etranger
)