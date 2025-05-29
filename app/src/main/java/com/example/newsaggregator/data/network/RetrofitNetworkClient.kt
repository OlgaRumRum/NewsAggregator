package com.example.newsaggregator.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.newsaggregator.data.NetworkClient
import com.example.newsaggregator.data.rss.RssFeed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitNetworkClient(
    private val rssFeed: RssFeed,
    private val context: Context
) : NetworkClient {
    override suspend fun doRequest(dto: Any): Response {
        if (!isConnected()) {
            return Response("No internet connection")
        }
        return withContext(Dispatchers.IO) {
            try {
                when (dto) {
                    is NewsRequest -> {
                        val rssDto = rssFeed.getRss(dto.query)
                        NewsResponse(rssDto = rssDto)
                    }

                    else -> {
                        Response("Unsupported request type")
                    }
                }
            } catch (e: Exception) {
                Response(e.message.toString())
            }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        return false
    }
}
