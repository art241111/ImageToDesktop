package com.art241111.imagetodesktop.ui.screens.photosList.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.ui.screens.photosList.PhotosListViewModel

/**
 * Option for displaying images in the table.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */
@Composable
fun PhotoGridView(
    modifier: Modifier = Modifier,
    images: LazyPagingItems<Image>,
    categoryListViewModel: PhotosListViewModel,
    onOpenPhotoPreview: (image: Image) -> Unit
) {
    if (images.loadState.refresh == LoadState.Loading) {
        LoadWaitingView()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        state = categoryListViewModel.gridState
    ) {
        items(images) { image ->
            if (image != null) {
                PhotoView(
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

fun <T : Any> LazyGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyGridScope.(value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
        this@items.itemContent(lazyPagingItems.getAsState(index).value)
    }
}
