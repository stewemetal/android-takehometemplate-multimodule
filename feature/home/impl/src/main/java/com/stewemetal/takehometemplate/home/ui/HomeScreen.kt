package com.stewemetal.takehometemplate.home.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onItemClick: (ItemId) -> Unit,
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            items(state.items) {
                ListItem(
                    modifier = Modifier
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(),
                        ) {
                            onItemClick(it.id)
                        },
                    headlineContent = {
                        Text(it.value)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val state by remember {
        mutableStateOf(
            HomeState(
                isLoading = false,
                items = listOf(
                    Item(ItemId(UUID.randomUUID().toString()), "a"),
                    Item(ItemId(UUID.randomUUID().toString()), "b"),
                    Item(ItemId(UUID.randomUUID().toString()), "c"),
                ),
            ),
        )
    }

    HomeScreen(
        state = state,
        onBackClick = { },
        onItemClick = { },
    )
}