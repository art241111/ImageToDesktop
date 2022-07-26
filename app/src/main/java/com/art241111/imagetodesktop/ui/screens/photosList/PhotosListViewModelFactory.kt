package com.art241111.imagetodesktop.ui.screens.photosList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.art241111.imagetodesktop.data.repository.MainRepository

/**
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class PhotosListViewModelFactory(
    private val repository: MainRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotosListViewModel(repository) as T
    }
}
