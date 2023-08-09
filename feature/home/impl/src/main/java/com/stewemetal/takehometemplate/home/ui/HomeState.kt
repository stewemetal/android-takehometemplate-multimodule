package com.stewemetal.takehometemplate.home.ui

import com.stewemetal.takehometemplate.shell.domain.Item

data class HomeState(
    val isLoading: Boolean = true,
    val items: List<Item> = emptyList(),
)
