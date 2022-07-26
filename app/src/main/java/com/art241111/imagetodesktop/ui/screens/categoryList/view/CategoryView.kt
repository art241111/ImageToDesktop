package com.art241111.imagetodesktop.ui.screens.categoryList.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.art241111.imagetodesktop.data.entity.Category

/**
 * Card for displaying information about the category.
 *
 * @param category - category information.
 * @param onClick - action when selecting a category.
 *
 * @author Created by Artem Gerasimov (gerasimov.av.dev@gmail.com).
 */

@ExperimentalMaterial3Api
@Composable
fun CategoryView(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.padding(5.dp), onClick = onClick) {
        Box(Modifier.height(100.dp)) {
            Column(Modifier.align(Alignment.Center)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = category.name,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
