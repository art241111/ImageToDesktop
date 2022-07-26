package com.art241111.imagetodesktop.ui.screens.photosList.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.art241111.imagetodesktop.R
import com.art241111.imagetodesktop.data.entity.PresentationOption

/**
 * The panel on which the back button is displayed and the choice
 * of how the list is presented is implemented (list or table)
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */
@Composable
fun ToolbarForImageList(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    presentationOption: PresentationOption,
    setPresentationOption: (PresentationOption) -> Unit
) {
    Row(modifier) {
        IconButton(

            onClick = { onBack() }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = {
                setPresentationOption(
                    if (presentationOption == PresentationOption.COLUMN) {
                        PresentationOption.GRID
                    } else {
                        PresentationOption.COLUMN
                    }
                )
            }
        ) {
            if (presentationOption == PresentationOption.GRID) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "List"
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_dashboard_24),
                    contentDescription = "Grid"
                )
            }
        }
    }
}
