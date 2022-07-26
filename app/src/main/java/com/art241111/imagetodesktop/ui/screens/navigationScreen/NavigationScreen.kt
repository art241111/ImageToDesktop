package com.art241111.imagetodesktop.ui.screens.navigationScreen

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.art241111.imagetodesktop.data.repository.MainRepositoryImpl
import com.art241111.imagetodesktop.ui.screens.Screens
import com.art241111.imagetodesktop.ui.screens.categoryList.CategoriesListScreen
import com.art241111.imagetodesktop.ui.screens.photoPreview.PhotoPreviewScreen
import com.art241111.imagetodesktop.ui.screens.photosList.PhotosListScreen
import com.art241111.imagetodesktop.utils.PhotoCategoryNames

/**
 * The screen that describes the navigation of the application.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NavigationScreen(
    navigation: Navigation,
    mainRepository: MainRepositoryImpl,
    photoCategories: PhotoCategoryNames,
    baseContext: Context
) {
    val screen by navigation.screen.collectAsState()

    when (screen) {
        Screens.CATEGORY_LIST -> {
            CategoriesListScreen(
                mainRepository = mainRepository,
                photoCategories = photoCategories,
                onOpenPhotoList = { category ->
                    navigation.openPhotosListScreen(category)
                }
            )
        }
        Screens.PHOTOS_LIST -> {
            if (navigation.category != null) {
                PhotosListScreen(
                    mainRepository = mainRepository,
                    category = navigation.category!!,
                    onBack = {
                        navigation.back()
                    },
                    onOpenPhotoPreview = { image ->
                        navigation.openPhotoPreviewScreen(image)
                    }
                )
            }
        }
        Screens.PHOTO_PREVIEW -> {
            if (navigation.image != null) {
                PhotoPreviewScreen(
                    image = navigation.image!!,
                    baseContext = baseContext,
                    onBack = { navigation.back() }
                )
            }
        }
    }
}
