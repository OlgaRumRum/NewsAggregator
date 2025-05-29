package com.example.newsaggregator.domain.api

import com.example.newsaggregator.domain.model.Item
import com.example.newsaggregator.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<Resource<List<Item>>>
}