package com.art241111.imagetodesktop.data.entity

/**
 * A class responsible for describing data delivery and storage.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T? = null, message: String): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T? = null): Resource<T> =
            Resource(status = Status.LOADING, data = data, message = null)
    }
}
