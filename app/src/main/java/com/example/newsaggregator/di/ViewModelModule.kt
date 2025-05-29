package com.example.newsaggregator.di

import com.example.newsaggregator.ui.NewsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        NewsViewModel(get())
    }
}