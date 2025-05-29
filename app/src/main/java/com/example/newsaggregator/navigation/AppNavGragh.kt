package com.example.newsaggregator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsScreenContent: @Composable () -> Unit,
    newsDetailsScreenContent: @Composable (guid: String) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.News.route
    ) {
        composable(route = Screen.News.route) {
            newsScreenContent()
        }
        composable(route = Screen.NewsDetails.route) {
            val guid = it.arguments?.getString(Screen.KEY_GUID) ?: ""
            newsDetailsScreenContent(guid)
        }
    }
}