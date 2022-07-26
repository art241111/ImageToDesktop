package com.art241111.imagetodesktop.ui.screens.categoryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.art241111.imagetodesktop.data.repository.MainRepository
import com.art241111.imagetodesktop.utils.PhotoCategoryNames

/**
 * Factory for creating a view model.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class CategoriesListViewModelFactory(
    private val photoCategories: PhotoCategoryNames,
    private val repository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoriesListViewModel(photoCategories, repository) as T
    }
}
