package com.art241111.imagetodesktop.data.entity

/**
 * An interface that describes which parameters are important for the image
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

interface Image {
    val name: String
    val description: String
    val smallImageUrl: String
    val fullImageUrl: String
}
