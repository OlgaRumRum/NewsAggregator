package com.example.newsaggregator.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsaggregator.data.rss.RssFeed
import com.example.newsaggregator.data.rss.dto.ItemDto
import kotlinx.coroutines.launch

@Composable
fun NewsScreen(rssFeed: RssFeed) {
    var items by remember { mutableStateOf<List<ItemDto>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            try {
                val rssDto = rssFeed.getRss()
                items = rssDto.channel.items
                isLoading = false
            } catch (e: Exception) {
                errorMessage = "Failed to load news: ${e.message}"
                isLoading = false
            }
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else if (errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Error: $errorMessage", modifier = Modifier.align(Alignment.Center))
        }
    } else {
        NewsLazyColumn(items = items)
    }
}

@Composable
fun NewsLazyColumn(items: List<ItemDto>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { item ->
            NewsItem(item = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNewsList() {
    val mockItems = listOf(
        ItemDto(
            title = "Mock News Title 1",
            link = "http://example.com/news1",
            description = "This is a mock news description. It provides information about the news article.",
            categories = emptyList(),
            pubDate = "2024-01-01",
            guid = "unique-id-1",
            contents = emptyList(),
            dcCreator = "Mock Creator",
            dcDate = "2024-01-01"
        ),
        ItemDto(
            title = "Mock News Title 2",
            link = "http://example.com/news2",
            description = "Another mock news description. This one is a bit longer to demonstrate text overflow.",
            categories = emptyList(),
            pubDate = "2024-01-02",
            guid = "unique-id-2",
            contents = emptyList(),
            dcCreator = "Mock Creator",
            dcDate = "2024-01-02"
        )
    )
    MaterialTheme {
        NewsLazyColumn(items = mockItems)
    }
}