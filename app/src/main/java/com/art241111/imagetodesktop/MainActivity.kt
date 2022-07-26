package com.art241111.imagetodesktop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.art241111.imagetodesktop.data.api.unsplash.UnsplashApiHelper
import com.art241111.imagetodesktop.data.repository.MainRepositoryImpl
import com.art241111.imagetodesktop.ui.screens.navigationScreen.NavigationScreen
import com.art241111.imagetodesktop.ui.screens.navigationScreen.NavigationViewModel
import com.art241111.imagetodesktop.ui.theme.ImageToDesktopTheme
import com.art241111.imagetodesktop.utils.PhotoCategoryNames

/**
 * Application launch point.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */
class MainActivity : ComponentActivity() {
    private val navigation: NavigationViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isConnect = mutableStateOf(true)
        val mainRepository = MainRepositoryImpl(
            UnsplashApiHelper(
                clientId = "g_eJiFd1Txgsvw4UpY9w5mt8ARyzCUFDzpPqDDHvuZs",
                context = baseContext,
                onConnectFailed = { isConnect.value = false },
                onConnectSuccess = { isConnect.value = true }
            )
        )

        val photoCategoryNames = PhotoCategoryNames()

        setContent {
            ImageToDesktopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        if (!isConnect.value) {
                            Box(
                                Modifier.fillMaxWidth().background(Color.Red),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Connection failed (offline mode)",
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            }
                        }
                        NavigationScreen(
                            navigation = navigation,
                            mainRepository = mainRepository,
                            photoCategories = photoCategoryNames,
                            baseContext = baseContext
                        )
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (navigation.screensCount() > 1) {
            navigation.back()
        } else {
            super.onBackPressed()
        }
    }
}
