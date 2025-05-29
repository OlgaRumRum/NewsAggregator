package com.example.newsaggregator.data.mappers

import com.example.newsaggregator.data.rss.dto.ItemDto
import com.example.newsaggregator.domain.model.Item

fun ItemDto.toItem(): Item {
    return Item(
        title = this.title,
        description = this.description,
        categories = this.categories.map { it.value },
        pubDate = this.pubDate,
        guid = this.guid,
        posterUrl = this.contents.last().url,
        dcCreator = this.dcCreator
    )
}