package com.art241111.imagetodesktop.data.entity

/**
 * A broader class describing the status, with the possibility of transmitting an error.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

data class AdvancedStatus(val status: Status, val message: String?) {
    companion object {
        fun success(): AdvancedStatus =
            AdvancedStatus(status = Status.SUCCESS, message = null)

        fun error(message: String): AdvancedStatus =
            AdvancedStatus(status = Status.ERROR, message = message)

        fun loading(): AdvancedStatus =
            AdvancedStatus(status = Status.LOADING, message = null)
    }
}
