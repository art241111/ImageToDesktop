package com.art241111.imagetodesktop.data.model.unsplash

import com.google.gson.annotations.SerializedName

/**
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

data class CollectionUnsplash(
    @SerializedName("total_pages")
    val totalPages: String?,
    @SerializedName("results")
    val results: List<ImageUnsplash>?
)
