package com.art241111.imagetodesktop.ui.screens.categoryList

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.repository.MainRepository
import com.art241111.imagetodesktop.ui.screens.categoryList.view.CategoryView
import com.art241111.imagetodesktop.utils.PhotoCategoryNames

/**
 * Output a list of categories.
 *
 * @param photoCategories - category list generator.
 * @param mainRepository - main repository is responsible for the distribution of requests,
 * should be able to do.
 * @param categoryListViewModel - viewmodel for this screen.
 * @param onOpenPhotoList - action when selecting a category.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

@ExperimentalMaterial3Api
@Composable
fun CategoriesListScreen(
    modifier: Modifier = Modifier,
    photoCategories: PhotoCategoryNames,
    mainRepository: MainRepository,
    categoryListViewModel: CategoriesListViewModel =
        viewModel(factory = CategoriesListViewModelFactory(photoCategories, mainRepository)),
    onOpenPhotoList: (category: Category) -> Unit
) {
    LaunchedEffect(mainRepository) {
        categoryListViewModel.getCategories()
    }
    val categories by categoryListViewModel.categories.collectAsState()

    LazyVerticalGrid(
        state = categoryListViewModel.listState,
        modifier = modifier,
        columns = GridCells.Fixed(2)
    ) {
        items(categories) { category ->
            CategoryView(
                category = category,
                onClick = {
                    onOpenPhotoList(category)
                }
            )
        }
    }
}
