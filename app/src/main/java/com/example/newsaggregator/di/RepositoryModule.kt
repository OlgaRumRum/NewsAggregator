package com.example.newsaggregator.di

import com.example.newsaggregator.data.repository.NewsRepositoryImpl
import com.example.newsaggregator.domain.api.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }
}