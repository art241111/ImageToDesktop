package com.art241111.imagetodesktop.data.repository

import com.art241111.imagetodesktop.data.api.ApiHelper
import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.data.entity.Resource

/**
 * Implementation of the main repository.
 * Main repository is responsible for the distribution of requests, should be able to do.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class MainRepositoryImpl(private val apiHelper: ApiHelper) : MainRepository {
    /**
     *  Getting photos from a certain category.
     *
     *  @param page - page number.
     *  @param category - required category.
     */
    override suspend fun getImagesFromCategory(
        category: Category,
        page: Int
    ): Resource<List<Image>> {
        return apiHelper.getImagesFromCategory(category.name, page)
    }
}
