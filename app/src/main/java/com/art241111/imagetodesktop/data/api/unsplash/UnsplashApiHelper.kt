package com.art241111.imagetodesktop.data.api.unsplash

import android.content.Context
import com.art241111.imagetodesktop.data.api.ApiHelper
import com.art241111.imagetodesktop.data.api.RetrofitBuilder.getUnsplashApiService
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.data.entity.ImageImpl
import com.art241111.imagetodesktop.data.entity.Resource
import com.art241111.imagetodesktop.data.model.unsplash.ImageUnsplash
import com.art241111.imagetodesktop.data.model.unsplash.Orientation

/**
 * The class is responsible for working with the Unsplash API.
 *
 * @param clientId - the class is responsible for working with the Unsplash API.
 * @param context -the context is required to obtain system
 * information about the network connection.
 * @param onConnectFailed - a function that is performed when there is no Internet access.
 * @param onConnectSuccess - a function that is performed when there is Internet access.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class UnsplashApiHelper(
    private val clientId: String,
    private val context: Context,
    private val onConnectFailed: () -> Unit,
    private val onConnectSuccess: () -> Unit
) : ApiHelper {
    /**
     *  Getting photos from a certain category.
     *
     *  @param category - required category.
     *  @param page - page number.
     *  @param orientation - image orientation.
     */
    override suspend fun getImagesFromCategory(
        category: String,
        page: Int,
        orientation: Orientation
    ): Resource<List<Image>> {
        return try {
            val list: List<Image> = getUnsplashApiService(
                context,
                onConnectFailed,
                onConnectSuccess
            ).getImagesFromCategory(
                page = page,
                query = category,
                clientId = clientId,
                orientation = orientation.name.lowercase()
            ).results?.map {
                unsplashImageToImage(it)
            } ?: emptyList()

            if (list.isEmpty()) {
                Resource.error(message = "Request to get a list of images - failed")
            } else {
                Resource.success(list)
            }
        } catch (e: Exception) {
            Resource.error(
                message = e.localizedMessage ?: "Request to get a list of images - failed"
            )
        }
    }

    private fun unsplashImageToImage(unsplashImage: ImageUnsplash): Image {
        return ImageImpl(
            name = unsplashImage.description ?: "",
            description = unsplashImage.description ?: "",
            smallImageUrl = (unsplashImage.urls?.small ?: ""),
            fullImageUrl = (unsplashImage.urls?.full ?: "")
        )
    }
}
