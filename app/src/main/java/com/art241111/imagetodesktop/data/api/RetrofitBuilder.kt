package com.art241111.imagetodesktop.data.api

import android.content.Context
import com.art241111.imagetodesktop.data.api.unsplash.UnsplashApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creating a retrofit instance to work with the API.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

object RetrofitBuilder {
    private const val UNSPLASH_URL = "https://api.unsplash.com/"

    /**
     * Returns an instance of the Retrofit class for working with the unsplash api.
     *
     * @param context -the context is required to obtain system
     * information about the network connection.
     * @param onConnectFailed - a function that is performed when there is no Internet access.
     * @param onConnectSuccess - a function that is performed when there is Internet access.
     */
    fun getUnsplashApiService(
        context: Context,
        onConnectFailed: () -> Unit,
        onConnectSuccess: () -> Unit
    ): UnsplashApiService =
        getRetrofit(
            context,
            onConnectFailed,
            onConnectSuccess,
            UNSPLASH_URL
        ).create(UnsplashApiService::class.java)

    /**
     * Creating a retrofit instance with certain parameters.
     *
     * @param context -the context is required to obtain system
     * information about the network connection.
     * @param onConnectFailed - a function that is performed when there is no Internet access.
     * @param onConnectSuccess - a function that is performed when there is Internet access.
     * @param url - the base url that retrofit will work with
     */
    private fun getRetrofit(
        context: Context,
        onConnectFailed: () -> Unit,
        onConnectSuccess: () -> Unit,
        url: String
    ): Retrofit {
        val oktHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                NetworkConnectionInterceptor(
                    context,
                    onConnectFailed,
                    onConnectSuccess
                )
            )

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(oktHttpClient.build())
            .build()
    }
}
