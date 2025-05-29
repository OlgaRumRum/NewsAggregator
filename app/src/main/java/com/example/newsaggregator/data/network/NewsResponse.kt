package com.example.newsaggregator.data.network

import com.example.newsaggregator.data.rss.dto.RssDto

data class NewsResponse(
    val rssDto: RssDto
) : Response()