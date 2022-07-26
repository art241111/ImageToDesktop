package com.art241111.imagetodesktop.data.model.unsplash

import com.google.gson.annotations.SerializedName

/**
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

data class UrlsUnsplash(
    @SerializedName("raw")
    val raw: String,
    @SerializedName("full")
    val full: String,
    @SerializedName("small")
    val small: String
)
