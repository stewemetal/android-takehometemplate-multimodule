package com.stewemetal.takehometemplate.home.ui

import com.stewemetal.takehometemplate.shell.domain.model.Item

data class HomeState(
    val isLoading: Boolean = true,
    val items: List<Item> = emptyList(),
)
