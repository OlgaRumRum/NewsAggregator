package com.example.newsaggregator.navigation

import android.net.Uri

sealed class Screen(val route: String) {
    data object News : Screen(ROUTE_HOME)
    data object NewsDetails : Screen(ROUTE_ARTICLE) {

        private const val ROUTE_FOR_ARGS = "news_details"

        fun getRouteWithArgs(guid: String): String {
            return "$ROUTE_FOR_ARGS/${Uri.encode(guid)}"
        }
    }

    companion object {

        const val KEY_GUID = "guid"

        private const val ROUTE_HOME = "news"
        private const val ROUTE_ARTICLE = "news_details/{$KEY_GUID}"
    }
}