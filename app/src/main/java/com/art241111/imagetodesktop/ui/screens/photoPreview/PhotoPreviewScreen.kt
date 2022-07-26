package com.art241111.imagetodesktop.ui.screens.photoPreview

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.art241111.imagetodesktop.data.entity.Image
import com.art241111.imagetodesktop.data.entity.Status
import com.art241111.imagetodesktop.ui.views.ProgressView

/**
 * Output screen of the selected image.
 *
 * The screen on which the selected image and button are displayed,
 * with the possibility of installing the image on the main screen.
 *
 * @param image - selected picture.
 * @param baseContext - context for the ability to upload an image to the main screen.
 * @param photoPreviewViewModel - viewmodel responsible for load image and
 * saving the image to the desktop.
 * @param onBack - actions to return to the previous screen.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

@Composable
fun PhotoPreviewScreen(
    modifier: Modifier = Modifier,
    image: Image,
    baseContext: Context,
    photoPreviewViewModel: PhotoPreviewViewModel =
        viewModel(factory = PhotoPreviewViewModelFactory(baseContext)),
    onBack: () -> Unit
) {
    LaunchedEffect(image) {
        photoPreviewViewModel.loadImage(image)
    }

    val imageBitmap by photoPreviewViewModel.image.collectAsState()
    val loadStatus by photoPreviewViewModel.loadStatus.collectAsState()
    val setWallpaperStatus by photoPreviewViewModel.setWallpaperStatus.collectAsState()

    Box(modifier) {
        when (loadStatus.status) {
            Status.LOADING -> {
                ProgressView()
            }
            Status.SUCCESS -> {
                if (imageBitmap != null) {
                    Image(
                        bitmap = imageBitmap!!.asImageBitmap(),
                        modifier = Modifier.fillMaxSize(),
                        contentDescription = "Image",
                        contentScale = ContentScale.Crop
                    )
                }
                Button(
                    modifier = Modifier.align(Alignment.BottomCenter)
                        .paddingFromBaseline(bottom = 10.dp),
                    onClick = {
                        photoPreviewViewModel.setWallpaper()
                    },
                    enabled = setWallpaperStatus.status != Status.LOADING
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (setWallpaperStatus.status == Status.LOADING) {
                            CircularProgressIndicator(color = Color.White)
                        } else {
                            Text("Set as wallpaper")
                        }
                    }
                }
            }
            Status.ERROR -> {
                Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(loadStatus.message ?: "The picture is not loaded")
                }
            }
        }

        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBack
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
        }
    }
}
