package com.stewemetal.takehometemplate.itemdetails.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.stewemetal.takehometemplate.shell.domain.Item
import com.stewemetal.takehometemplate.shell.domain.ItemId

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ItemDetailsScreen(
    state: ItemDetailsState,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Home")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Filled.ArrowBack, null)
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
                    id = ItemId(1u),
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
