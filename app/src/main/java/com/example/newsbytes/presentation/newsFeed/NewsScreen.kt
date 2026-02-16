package com.example.newsbytes.presentation.newsFeed

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsbytes.R
import com.example.newsbytes.core.util.launchUrlInCustomTab

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel = hiltViewModel(),
) {
    val newsFlow = newsViewModel.newsFlow.collectAsLazyPagingItems()
    val context = LocalContext.current
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            val isRefreshing = newsFlow.loadState.refresh is LoadState.Loading

            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = { newsFlow.refresh() },
                modifier = Modifier.fillMaxSize()
            ) {

                when (newsFlow.loadState.refresh) {
                    is LoadState.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    is LoadState.Error -> {
                        ErrorScreen(
                            textId = R.string.failed_to_load_news,
                            modifier = Modifier.align(Alignment.Center),
                            onRetryClicked = { newsFlow.retry() })
                    }

                    else -> {
                        val pagerState = rememberPagerState(pageCount = { newsFlow.itemCount })
                        VerticalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize(),
                        ) { page ->
                            val article = newsFlow[page]
                            if (article != null) {
                                NewsCard(article = article, onReadMoreClicked = {
                                    context.launchUrlInCustomTab(article.sourceUrl)
                                })
                            }
                        }
                    }
                }
                if (newsFlow.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 80.dp) // Keep it above the Read More button
                    )
                }
                if (newsFlow.loadState.append is LoadState.Error) {
                    ErrorScreen(
                        textId = R.string.something_went_wrong,
                        modifier = Modifier.align(Alignment.Center),
                        onRetryClicked = { newsFlow.retry() })
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(
    textId: Int, modifier: Modifier, onRetryClicked: () -> Unit
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(textId))
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = onRetryClicked
        ) {
            Text(text = stringResource(R.string.retry))
        }
    }
}
