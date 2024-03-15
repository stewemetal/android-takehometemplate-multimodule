package com.stewemetal.takehometemplate.itemdetails.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.stewemetal.takehometemplate.shell.domain.model.Item
import com.stewemetal.takehometemplate.shell.domain.model.ItemId
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(
    state: ItemDetailsState,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(state.item?.value ?: "")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                },
            )
        },
    ) { paddingValues ->
        Text(
            text = state.item?.value ?: "",
            modifier = Modifier.padding(paddingValues),
        )
    }
}

@Preview
@Composable
fun ItemDetailsScreenPreview() {
    val state by remember {
        mutableStateOf(
            ItemDetailsState(
                isLoading = false,
                item = Item(
                    id = ItemId(UUID.randomUUID().toString()),
                    value = "Preview"
                ),
            ),
        )
    }

    ItemDetailsScreen(
        state = state,
        onBackClick = { },
    )
}
