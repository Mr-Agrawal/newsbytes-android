package com.example.newsbytes.data.remote

import com.example.newsbytes.data.remote.dto.OnlineArticleListWithCount
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getNewsArticles(
        @Query("category") category: String = "general",
        @Query("lang") lang: String = "en",
        @Query("country") country: String = "in",
        @Query("max") max: Int = 10,
        @Query("page") page: Int = 1,
    ): OnlineArticleListWithCount
}