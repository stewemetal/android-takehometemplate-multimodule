package com.stewemetal.takehometemplate.shell.domain.usecase

import com.stewemetal.takehometemplate.shell.architecture.DispatcherProvider
import com.stewemetal.takehometemplate.shell.domain.model.Item
import com.stewemetal.takehometemplate.shell.repository.ItemsRepository
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Singleton

@Singleton
class GetItemsUseCase(
    private val itemsRepository: ItemsRepository,
    private val dispatcherProvider: DispatcherProvider,
) {
    suspend fun getItems(): List<Item> =
        withContext(dispatcherProvider.io) {
            itemsRepository.getItems()
        }
}
