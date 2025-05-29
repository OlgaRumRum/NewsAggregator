package com.example.newsaggregator.ui.newsdetails

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun NewsDetailsScreen(
    modifier: Modifier,
    guid: String
) {
    var isLoading by remember { mutableStateOf(true) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(
                            view: WebView?,
                            guid: String?,
                            favicon: android.graphics.Bitmap?
                        ) {
                            super.onPageStarted(view, guid, favicon)
                            isLoading = true
                        }

                        override fun onPageFinished(view: WebView?, guid: String?) {
                            super.onPageFinished(view, guid)
                            isLoading = false
                        }
                    }
                    loadUrl(guid)
                }
            },
            update = { webView ->
                webView.loadUrl(guid)
            }
        )

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}