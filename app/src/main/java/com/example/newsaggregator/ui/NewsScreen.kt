package com.example.newsaggregator.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.newsaggregator.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {

    val viewModel = koinViewModel<NewsViewModel>()

    val state = viewModel.state

    when (state) {
        is NewsScreenState.Content -> {
            NewsLazyColumn(
                modifier = modifier,
                items = state.itemList,
                onItemClick = onItemClick
            )
        }

        is NewsScreenState.Error -> {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = stringResource(R.string.something_went_wrong))
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {viewModel.getNews()}) {
                        Text(text = stringResource(R.string.update))
                    }
                }

            }
        }

        NewsScreenState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}