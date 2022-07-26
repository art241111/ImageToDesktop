package com.art241111.imagetodesktop.data.api

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Checking the network connection.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */
class NetworkConnectionInterceptor(
    private val context: Context,
    private val onConnectFailed: () -> Unit,
    private val onConnectSuccess: () -> Unit
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            onConnectFailed()
            throw NoConnectivityException()
        }
        onConnectSuccess()
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}

class NoConnectivityException : IOException() {
    // You can send any message whatever you want from here.
    override val message: String
        get() = "No Internet Connection"
    // You can send any message whatever you want from here.
}
