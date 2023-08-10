package com.stewemetal.takehometemplate.shell.repository

import com.stewemetal.takehometemplate.shell.domain.model.Item
import com.stewemetal.takehometemplate.shell.domain.model.ItemId
import org.koin.core.annotation.Singleton
import java.util.UUID

@Singleton
class ItemsRepository {
    private val items = listOf(
        Item(ItemId(UUID.randomUUID().toString()), "a"),
        Item(ItemId(UUID.randomUUID().toString()), "b"),
        Item(ItemId(UUID.randomUUID().toString()), "c"),
    )

    fun getItems() = items

    fun getItem(id: ItemId) = items.first { it.id == id }
}
