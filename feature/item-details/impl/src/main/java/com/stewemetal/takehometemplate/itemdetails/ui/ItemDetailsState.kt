package com.stewemetal.takehometemplate.itemdetails.ui

import com.stewemetal.takehometemplate.shell.domain.Item

data class ItemDetailsState(
    val isLoading: Boolean = true,
    val item: Item? = null,
)
