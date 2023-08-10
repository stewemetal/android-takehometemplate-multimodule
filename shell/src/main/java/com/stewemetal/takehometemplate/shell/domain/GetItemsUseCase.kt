package com.stewemetal.takehometemplate.shell.domain

import com.stewemetal.takehometemplate.shell.domain.model.Item
import org.koin.core.annotation.Singleton

@Singleton
class GetItemsUseCase(
    private val itemsRepository: ItemsRepository,
) {
    suspend fun getItems(): List<Item> =
        itemsRepository.getItems()
}
