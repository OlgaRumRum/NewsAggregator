package com.example.newsaggregator.ui.news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsaggregator.domain.model.Resource
import com.example.newsaggregator.domain.usecases.GetNewsUseCase
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    var state: NewsScreenState by mutableStateOf(NewsScreenState.Loading)
        private set

    init {
        getNews()
    }

    fun getNews() {
        state = NewsScreenState.Loading
        viewModelScope.launch {
            getNewsUseCase().collect { result ->
                state = when (result) {
                    is Resource.Success -> {
                        NewsScreenState.Content(result.data)
                    }

                    is Resource.Error -> {
                        NewsScreenState.Error(message = result.errorCode)
                    }
                }
            }
        }
    }
}