package com.stewemetal.takehometemplate.itemlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.ripple.rememberRipple
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stewemetal.takehometemplate.shell.domain.model.Item
import com.stewemetal.takehometemplate.shell.domain.model.ItemId
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ItemListScreen(
    state: ItemListState,
    onItemClick: (ItemId) -> Unit,
    onAppBarNavigationClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Items")
                },
                navigationIcon = {
                    IconButton(onClick = onAppBarNavigationClick) {
                        Icon(Icons.Filled.Menu, null)
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
            items(state.items) { item ->
                ItemListItem(
                    item = item,
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(),
                        ) {
                            onItemClick(item.id)
                        },
                )
            }
        }
    }
}

@Composable
fun ItemListItem(
    item: Item,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(item.value)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val state by remember {
        mutableStateOf(
            ItemListState(
                isLoading = false,
                items = listOf(
                    Item(ItemId(UUID.randomUUID().toString()), "a"),
                    Item(ItemId(UUID.randomUUID().toString()), "b"),
                    Item(ItemId(UUID.randomUUID().toString()), "c"),
                ),
            ),
        )
    }

    ItemListScreen(
        state = state,
        onAppBarNavigationClick = { },
        onItemClick = { },
    )
}
