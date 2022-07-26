package com.art241111.imagetodesktop.data.api.unsplash

import com.art241111.imagetodesktop.data.model.unsplash.CollectionUnsplash
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * An interface that describes requests to the Unsplash API.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

interface UnsplashApiService {
    /**
     *  Getting photos from a certain category.
     *
     *  @param page - page number.
     *  @param query - required category.
     *  @param clientId - the class is responsible for working with the Unsplash API.
     *  @param orientation - image orientation.
     */
    @GET("search/photos")
    suspend fun getImagesFromCategory(
        @Query("page") page: Int,
        @Query("query") query: String,
        @Query("client_id") clientId: String,
        @Query("orientation") orientation: String
    ): CollectionUnsplash
}
