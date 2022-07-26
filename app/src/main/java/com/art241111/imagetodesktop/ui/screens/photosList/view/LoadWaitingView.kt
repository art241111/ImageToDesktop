package com.art241111.imagetodesktop.ui.screens.photosList.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.art241111.imagetodesktop.ui.views.ProgressView

/**
 * View to describe the loading process.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */
@Composable
fun LoadWaitingView(
    modifier: Modifier = Modifier
) {
    ProgressView(modifier, text = "Waiting for items to load from the backend")
}
