package com.example.newsbytes.domain.repository

import androidx.paging.PagingData
import com.example.newsbytes.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getPagedNewsArticle(): Flow<PagingData<Article>>

}