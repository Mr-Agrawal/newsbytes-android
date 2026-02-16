package com.example.newsbytes.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsbytes.data.remote.dto.ArticleDto

class NewsPagingSource(private val apiService: NewsApiService) : PagingSource<Int, ArticleDto>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getNewsArticles(page = page, max = params.loadSize)
            val articles = response.articles
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if(articles.isEmpty()) null else page + 1
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
