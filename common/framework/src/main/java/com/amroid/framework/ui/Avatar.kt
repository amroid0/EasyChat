package com.amroid.framework.ui

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade


@Composable
fun Avatar(avatarUrl: String, size: Dp, contentDescription: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(avatarUrl)
            .crossfade(true)
            .build(), contentDescription = contentDescription, modifier = Modifier
            .size(size)
            .clip(
                CircleShape
            ),
        contentScale = ContentScale.Crop,
        placeholder = ColorPainter(Color.Gray),

        onError = { Log.e("Coil", "Image loading failed: ${it.result.throwable}") }
    )
}