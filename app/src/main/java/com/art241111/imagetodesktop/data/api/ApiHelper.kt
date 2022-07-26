package com.art241111.imagetodesktop.data.api

import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.data.entity.Resource
import com.art241111.imagetodesktop.data.model.unsplash.Orientation

/**
 * An interface that describes what actions should occur with each API.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

interface ApiHelper {
    /**
     *  Getting photos from a certain category.
     *
     *  @param category - required category.
     *  @param page - page number.
     *  @param orientation - image orientation.
     */
    suspend fun getImagesFromCategory(
        category: String,
        page: Int,
        orientation: Orientation = Orientation.PORTRAIT
    ): Resource<List<Image>>
}
