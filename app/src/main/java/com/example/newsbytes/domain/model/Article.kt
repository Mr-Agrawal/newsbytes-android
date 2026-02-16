package com.example.newsbytes.domain.model

data class Article(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val sourceUrl: String,
    val publishedAt: String,
)