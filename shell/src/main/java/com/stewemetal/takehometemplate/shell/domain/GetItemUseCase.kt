package com.stewemetal.takehometemplate.shell.domain

import com.stewemetal.takehometemplate.shell.domain.model.Item
import com.stewemetal.takehometemplate.shell.domain.model.ItemId
import org.koin.core.annotation.Singleton

@Singleton
class GetItemUseCase(
    private val itemsRepository: ItemsRepository,
) {
    suspend fun getItem(id: ItemId): Item =
        itemsRepository.getItem(id)
}
