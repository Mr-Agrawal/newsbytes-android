package com.example.newsbytes.data.remote.dto

import com.example.newsbytes.domain.model.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("content") val content: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("id") val id: String? = null,
    @SerialName("image") val image: String? = null,
    @SerialName("lang") val lang: String? = null,
    @SerialName("publishedAt") val publishedAt: String? = null,
    @SerialName("source") val source: Source? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("url") val url: String? = null
)

fun ArticleDto.toArticle(): Article {
    return Article(
        id = id ?: "",
        title = title ?: "No Title Available",
        description = description ?: "No description available.",
        imageUrl = image ?: "",
        sourceUrl = url ?: "",
        publishedAt = publishedAt ?: ""
    )
}

@Serializable
data class OnlineArticleListWithCount(
    val articles: List<ArticleDto>,
    val information: Information,
    val totalArticles: Int,
)

@Serializable
data class Source(
    @SerialName("id") val id: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String? = null
)

@Serializable
data class Information(
    val realTimeArticles: RealTimeArticles
)

@Serializable
data class RealTimeArticles(
    val message: String
)