package com.example.newsaggregator.domain.model

sealed class Resource<T> {
    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val errorCode: String) : Resource<T>()
}