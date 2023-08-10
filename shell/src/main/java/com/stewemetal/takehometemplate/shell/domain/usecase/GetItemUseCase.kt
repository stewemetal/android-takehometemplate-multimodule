package com.stewemetal.takehometemplate.shell.domain.usecase

import com.stewemetal.takehometemplate.shell.architecture.DispatcherProvider
import com.stewemetal.takehometemplate.shell.domain.model.Item
import com.stewemetal.takehometemplate.shell.domain.model.ItemId
import com.stewemetal.takehometemplate.shell.repository.ItemsRepository
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Singleton

@Singleton
class GetItemUseCase(
    private val itemsRepository: ItemsRepository,
    private val dispatcherProvider: DispatcherProvider,
) {
    suspend fun getItem(id: ItemId): Item =
        withContext(dispatcherProvider.io) {
            itemsRepository.getItem(id)
        }
}
