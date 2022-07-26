package com.art241111.imagetodesktop.ui.screens.photoPreview

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.art241111.imagetodesktop.data.entity.AdvancedStatus
import com.art241111.imagetodesktop.data.entity.Image
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Viewmodel responsible for load image and saving the image to the desktop.
 *
 * @param baseContext - context for the ability to upload an image to the main screen.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

class PhotoPreviewViewModel(private val baseContext: Context) : ViewModel() {
    private val _loadStatus = MutableStateFlow<AdvancedStatus>(AdvancedStatus.loading())
    val loadStatus: StateFlow<AdvancedStatus> = _loadStatus

    private val _setWallpaperStatus = MutableStateFlow<AdvancedStatus>(AdvancedStatus.success())
    val setWallpaperStatus: StateFlow<AdvancedStatus> = _setWallpaperStatus

    private val _image = MutableStateFlow<Bitmap?>(null)
    val image: StateFlow<Bitmap?> = _image

    /**
     * Loading bitmap into a local variable.
     *
     * @param image - selected images.
     */
    fun loadImage(image: Image) {
        viewModelScope.launch(Dispatchers.IO) {
            _loadStatus.value = AdvancedStatus.loading()

            Glide.with(baseContext)
                .asBitmap()
                .load(image.fullImageUrl)
                // Reducing the image size for rendering on all devices
                .apply(RequestOptions().override(2400))
                .listener(
                    object : RequestListener<Bitmap?> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap?>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            _loadStatus.value = AdvancedStatus.error(
                                message = e?.localizedMessage ?: "The picture is not loaded"
                            )
                            return false
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap?>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            _loadStatus.value = AdvancedStatus.success()
                            _image.value = resource

                            return false
                        }
                    }
                ).submit()
        }
    }

    /**
     * Installing an image on the desktop.
     */
    fun setWallpaper() {
        viewModelScope.launch(Dispatchers.IO) {
            _setWallpaperStatus.value = AdvancedStatus.loading()
            if (image.value != null) {
                try {
                    val wallpaperManager = WallpaperManager.getInstance(baseContext)
                    wallpaperManager.setBitmap(image.value)
                    _setWallpaperStatus.value = AdvancedStatus.success()
                } catch (e: Exception) {
                    _setWallpaperStatus.value = AdvancedStatus.error(
                        e?.localizedMessage ?: "The installation of the image on the desktop failed"
                    )
                }
            } else {
                _setWallpaperStatus.value = AdvancedStatus.error(
                    message = "The picture is not loaded"
                )
            }
        }
    }
}
