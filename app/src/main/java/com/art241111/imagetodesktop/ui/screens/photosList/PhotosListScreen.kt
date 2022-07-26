package com.art241111.imagetodesktop.ui.screens.photosList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.data.entity.PresentationOption
import com.art241111.imagetodesktop.data.repository.MainRepository
import com.art241111.imagetodesktop.ui.screens.photosList.view.PhotoGridView
import com.art241111.imagetodesktop.ui.screens.photosList.view.PhotoListView
import com.art241111.imagetodesktop.ui.screens.photosList.view.ToolbarForImageList

/**
 * Output a list of photos from a specific category.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

@ExperimentalFoundationApi
@Composable
fun PhotosListScreen(
    modifier: Modifier = Modifier,
    mainRepository: MainRepository,
    category: Category,
    categoryListViewModel: PhotosListViewModel =
        viewModel(factory = PhotosListViewModelFactory(mainRepository)),
    onBack: () -> Unit,
    onOpenPhotoPreview: (image: Image) -> Unit
) {
    var presentationOption by remember { mutableStateOf(PresentationOption.COLUMN) }
    Column(modifier.fillMaxSize()) {
        ToolbarForImageList(
            onBack = onBack,
            presentationOption = presentationOption,
            setPresentationOption = {
                presentationOption = it
            }
        )

        val images = categoryListViewModel.getImagePagination(category).collectAsLazyPagingItems()

        when (presentationOption) {
            PresentationOption.COLUMN -> {
                PhotoListView(
                    images = images,
                    categoryListViewModel = categoryListViewModel,
                    onOpenPhotoPreview = onOpenPhotoPreview
                )
            }

            PresentationOption.GRID -> {
                PhotoGridView(
                    images = images,
                    categoryListViewModel = categoryListViewModel,
                    onOpenPhotoPreview = onOpenPhotoPreview
                )
            }
        }
    }
}
