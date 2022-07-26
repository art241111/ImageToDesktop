package com.art241111.imagetodesktop.data.entity

/**
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

data class ImageImpl(
    override val name: String,
    override val description: String,
    override val smallImageUrl: String,
    override val fullImageUrl: String
) : Image
