package com.art241111.imagetodesktop.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.data.entity.Status
import com.art241111.imagetodesktop.data.repository.MainRepository

/**
 * The class is responsible for pagination of images.
 *
 * @param mainRepository - the main repository that distributes requests.
 * @param category - required category.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */
class ImageSource(
    private val mainRepository: MainRepository,
    private val category: Category
) : PagingSource<Int, Image>() {
    /**
     * The function is responsible for loading images.
     *
     * @param params - page number.
     *
     * @return Returns the page number and before the uploaded image.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Image> {
        return try {
            val page = params.key ?: 1
            val photoResponse = mainRepository.getImagesFromCategory(category, page)
            if (photoResponse.status == Status.SUCCESS) {
                LoadResult.Page(
                    data = photoResponse.data as List<Image>,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = page.plus(1)
                )
            } else {
                LoadResult.Error(
                    Exception(
                        photoResponse.message ?: "Request to get a list of images - failed"
                    )

                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    /**
     * Responsible for the correct reloading when updating the list from the API.
     */
    override fun getRefreshKey(state: PagingState<Int, Image>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
