package com.example.newsaggregator.domain.usecases

import com.example.newsaggregator.domain.api.NewsRepository
import com.example.newsaggregator.domain.model.Item
import com.example.newsaggregator.domain.model.Resource
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<Item>>> {
        return repository.getNews()
    }
}