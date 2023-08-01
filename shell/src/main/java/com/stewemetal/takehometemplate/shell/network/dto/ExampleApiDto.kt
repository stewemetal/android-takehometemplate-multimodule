package com.stewemetal.takehometemplate.shell.network.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ExampleApiDto(
    val id: String,
    val content: String,
)
