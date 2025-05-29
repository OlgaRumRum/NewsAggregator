package com.example.newsaggregator.ui.news

import com.example.newsaggregator.domain.model.Item

sealed interface NewsScreenState {
    data object Loading : NewsScreenState

    data class Content(val itemList: List<Item>) : NewsScreenState

    data class Error(val message: String) : NewsScreenState
}