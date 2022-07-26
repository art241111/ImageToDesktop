package com.art241111.imagetodesktop.data.model.unsplash

import com.google.gson.annotations.SerializedName

/**
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

data class ImageUnsplash(
    @SerializedName("id")
    val id: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("urls")
    val urls: UrlsUnsplash?,
    @SerializedName("links")
    val links: LinksUnsplash?
)
