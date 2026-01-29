package com.example.login_signup_kotlin.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Upsert
    suspend fun upsertArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("SELECT * FROM article ORDER BY designation ASC")
    fun getOrderedByDesignation(): Flow<List<Article>>
}
