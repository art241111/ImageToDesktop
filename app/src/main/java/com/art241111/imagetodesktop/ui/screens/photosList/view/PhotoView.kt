package com.art241111.imagetodesktop.ui.screens.photosList.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.ui.views.ImageView

/**
 * Output of an image that can be clicked on.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

@Composable
fun PhotoView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    image: Image
) {
    Column(
        modifier.clickable {
            onClick()
        }
    ) {
        ImageView(
            modifier = Modifier.fillMaxSize(),
            imageUrl = image.smallImageUrl
        )
    }
}
