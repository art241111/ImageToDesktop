package com.art241111.imagetodesktop.ui.screens.categoryList

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.lifecycle.ViewModel
import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.repository.MainRepository
import com.art241111.imagetodesktop.utils.PhotoCategoryNames
import kotlinx.coroutines.flow.MutableStateFlow

/**
 *  Viewmodel for category list screen.
 *
 * @param photoCategories - category list generator.
 * @param repository - main repository is responsible for the distribution of requests,
 * should be able to do.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class CategoriesListViewModel(
    private val photoCategories: PhotoCategoryNames,
    private val repository: MainRepository
) : ViewModel() {
    val listState: LazyGridState = LazyGridState()

    var categories: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())

    fun getCategories() {
        categories.value = photoCategories.categories.map { Category(it) }
    }
}
