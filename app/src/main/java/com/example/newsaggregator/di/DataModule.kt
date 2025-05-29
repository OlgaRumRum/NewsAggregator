package com.example.newsaggregator.di

import com.example.newsaggregator.data.NetworkClient
import com.example.newsaggregator.data.network.RetrofitNetworkClient
import com.example.newsaggregator.data.rss.RssFeed
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import nl.adaptivity.xmlutil.serialization.XML
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val dataModule = module {
    single<RssFeed> {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()

        Retrofit.Builder()
            .baseUrl("https://www.theguardian.com")
            .addConverterFactory(
                XML.asConverterFactory(
                    "application/xml; charset=UTF8".toMediaType()
                )
            )
            .client(okHttpClient)
            .build()
            .create()
    }
    single<NetworkClient> {
        RetrofitNetworkClient(get(), get())
    }
}