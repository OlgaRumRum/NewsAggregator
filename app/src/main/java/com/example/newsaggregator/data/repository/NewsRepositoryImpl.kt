package com.example.newsaggregator.data.repository

import com.example.newsaggregator.data.NetworkClient
import com.example.newsaggregator.data.mappers.toItem
import com.example.newsaggregator.data.network.NewsRequest
import com.example.newsaggregator.data.network.NewsResponse
import com.example.newsaggregator.domain.api.NewsRepository
import com.example.newsaggregator.domain.model.Item
import com.example.newsaggregator.domain.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val networkClient: NetworkClient
) : NewsRepository {
    override fun getNews(): Flow<Resource<List<Item>>> = flow {
        val response = networkClient.doRequest(NewsRequest())

        if (response is NewsResponse) {
            val news = response.rssDto.channel.items.map { it.toItem() }
            emit(Resource.Success(news))
        } else {
            emit(Resource.Error(response.resultCode))
        }
    }
}