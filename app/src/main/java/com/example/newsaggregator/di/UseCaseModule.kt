package com.example.newsaggregator.di

import com.example.newsaggregator.domain.usecases.GetNewsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetNewsUseCase(get())
    }
}