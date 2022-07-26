package com.art241111.imagetodesktop.ui.screens.photosList.view

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.ui.screens.photosList.PhotosListViewModel

/**
 * Option for displaying images in the table.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

@Composable
fun PhotoListView(
    modifier: Modifier = Modifier,
    images: LazyPagingItems<Image>,
    categoryListViewModel: PhotosListViewModel,
    onOpenPhotoPreview: (image: Image) -> Unit
) {
    if (images.loadState.refresh == LoadState.Loading) {
        LoadWaitingView()
    }

    LazyColumn(modifier = modifier.fillMaxSize(), state = categoryListViewModel.listState) {
        items(images) { image ->
            if (image != null) {
                PhotoView(
                    modifier = Modifier.defaultMinSize(100.dp, 100.dp),
                    onClick = {
                        onOpenPhotoPreview(image)
                    },
                    image = image
                )
            }
        }

        if (images.loadState.append == LoadState.Loading) {
            item {
                AdditionalLoadWaitingView()
            }
        }
    }
}
