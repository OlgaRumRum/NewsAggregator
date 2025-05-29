package com.example.newsaggregator.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsaggregator.navigation.AppNavGraph
import com.example.newsaggregator.navigation.Screen
import com.example.newsaggregator.navigation.rememberNavigationState
import com.example.newsaggregator.ui.newsdetails.NewsDetailsScreen
import com.example.newsaggregator.ui.news.NewsScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
    ) { innerPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsScreenContent = {
                NewsScreen(
                    modifier = Modifier.padding(innerPadding),
                    onItemClick = {
                        navigationState.navigateTo(Screen.NewsDetails.getRouteWithArgs(it))
                    }
                )
            },
            newsDetailsScreenContent = {
                NewsDetailsScreen(
                    modifier = Modifier.padding(innerPadding),
                    guid = it
                )
            },
        )
    }
}