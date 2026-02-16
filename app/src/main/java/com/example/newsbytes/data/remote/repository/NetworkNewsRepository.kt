package com.example.newsbytes.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.newsbytes.data.remote.NewsApiService
import com.example.newsbytes.data.remote.NewsPagingSource
import com.example.newsbytes.data.remote.dto.toArticle
import com.example.newsbytes.domain.model.Article
import com.example.newsbytes.domain.repository.NewsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NetworkNewsRepository @Inject constructor(private val apiService: NewsApiService) :
    NewsRepository {

    override fun getPagedNewsArticle(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,          //decides params.loadSize
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(apiService)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toArticle() }
        }
    }
}