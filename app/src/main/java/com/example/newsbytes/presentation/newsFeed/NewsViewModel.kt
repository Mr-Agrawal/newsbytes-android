package com.example.newsbytes.presentation.newsFeed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsbytes.domain.model.Article
import com.example.newsbytes.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {
    val newsFlow: Flow<PagingData<Article>> =
        newsRepository.getPagedNewsArticle().cachedIn(viewModelScope)


}