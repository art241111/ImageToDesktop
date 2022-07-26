package com.art241111.imagetodesktop.ui.screens.photoPreview

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for creating a view model.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class PhotoPreviewViewModelFactory(private val baseContext: Context) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PhotoPreviewViewModel(baseContext) as T
    }
}
