package com.art241111.imagetodesktop.ui.screens.navigationScreen

import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.ui.screens.Screens
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface describing navigation in the application.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

interface Navigation {
    val screen: StateFlow<Screens>
    val category: Category?
    val image: Image?
    fun openPhotosListScreen(category: Category)
    fun openPhotoPreviewScreen(image: Image)
    fun back()
}
