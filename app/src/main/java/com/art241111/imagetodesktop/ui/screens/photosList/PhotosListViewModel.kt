package com.art241111.imagetodesktop.ui.screens.photosList

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.art241111.imagetodesktop.data.api.ImageSource
import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.data.entity.PresentationOption
import com.art241111.imagetodesktop.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

/**
 * Viewmodel for photo list screen.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class PhotosListViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val listState: LazyListState = LazyListState()
    val gridState: LazyGridState = LazyGridState()
    var presentationOption by mutableStateOf(PresentationOption.COLUMN)

    fun getImagePagination(category: Category): Flow<PagingData<Image>> {
        return Pager(PagingConfig(pageSize = 20)) {
            ImageSource(repository, category)
        }.flow
    }
}
