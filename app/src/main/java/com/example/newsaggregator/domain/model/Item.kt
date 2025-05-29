package com.example.newsaggregator.domain.model

data class Item(
    val title: String,
    val description: String,
    val categories: List<String>,
    val pubDate: String,
    val guid: String,
    val posterUrl: String,
    val dcCreator: String,
)
