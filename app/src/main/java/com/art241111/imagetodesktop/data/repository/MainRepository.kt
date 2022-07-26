package com.art241111.imagetodesktop.data.repository

import com.art241111.imagetodesktop.data.entity.Category
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.data.entity.Resource

/**
 * An interface describing what the main repository.
 * Main repository is responsible for the distribution of requests, should be able to do.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

interface MainRepository {
    /**
     *  Getting photos from a certain category.
     *
     *  @param page - page number.
     *  @param category - required category.
     */
    suspend fun getImagesFromCategory(
        category: Category,
        page: Int
    ): Resource<List<Image>>
}
