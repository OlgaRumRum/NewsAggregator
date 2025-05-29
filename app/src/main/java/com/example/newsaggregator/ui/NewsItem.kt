package com.example.newsaggregator.ui

import android.graphics.Color
import android.graphics.text.LineBreaker
import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.view.Gravity
import android.widget.TextView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.example.newsaggregator.R
import com.example.newsaggregator.domain.model.Item
import com.example.newsaggregator.utils.formatDate

@Composable
fun NewsItem(
    item: Item,
    onItemClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item.guid) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column {

            val description = Html.fromHtml(item.description, Html.FROM_HTML_MODE_COMPACT)

            AsyncImage(
                model = item.posterUrl,
                contentDescription = stringResource(R.string.news_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                text = item.title,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            AndroidView(
                modifier = Modifier.padding(horizontal = 10.dp),
                factory = { context ->
                    TextView(context).apply {
                        gravity = Gravity.FILL
                        maxLines = 10
                        ellipsize = TextUtils.TruncateAt.END
                        text = text
                        textSize = 16f
                        setTextColor(Color.BLACK)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
                        }
                    }
                },
                update = { textView ->
                    textView.setText(description)
                }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                modifier = Modifier.padding(horizontal = 10.dp),
                text = item.dcCreator,
                style = MaterialTheme.typography.displayMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.padding(8.dp),
                text = "Published on ${formatDate(item.pubDate)}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

