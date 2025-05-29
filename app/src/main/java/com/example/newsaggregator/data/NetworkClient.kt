package com.example.newsaggregator.data

import com.example.newsaggregator.data.network.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}