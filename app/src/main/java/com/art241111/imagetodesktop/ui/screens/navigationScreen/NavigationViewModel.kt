package com.art241111.imagetodesktop.ui.screens.navigationScreen

import androidx.lifecycle.ViewModel
import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.ui.screens.Screens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Application navigation implementation.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class NavigationViewModel : Navigation, ViewModel() {
    private var _screens = MutableStateFlow(listOf(Screens.CATEGORY_LIST))
    private val _screen = MutableStateFlow(Screens.CATEGORY_LIST)

    override val screen: StateFlow<Screens> = _screen
    override var category: Category? = null
        private set

    override var image: Image? = null
        private set

    fun screensCount() = _screens.value.size

    override fun openPhotosListScreen(category: Category) {
        openScreen(Screens.PHOTOS_LIST)
        this.category = category
    }

    override fun openPhotoPreviewScreen(image: Image) {
        openScreen(Screens.PHOTO_PREVIEW)
        this.image = image
    }

    override fun back() {
        val screens = mutableListOf<Screens>()
        screens.addAll(_screens.value)
        screens.removeLast()
        _screens.value = screens
        _screen.value = screens.last()
    }

    private fun openScreen(screen: Screens) {
        val screens = mutableListOf<Screens>()
        screens.addAll(_screens.value)
        screens.add(Screens.PHOTOS_LIST)

        _screens.value = screens
        _screen.value = screen
    }
}
