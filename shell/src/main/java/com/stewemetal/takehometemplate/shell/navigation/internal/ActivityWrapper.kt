package com.stewemetal.takehometemplate.shell.navigation.internal

import androidx.appcompat.app.AppCompatActivity

internal data class ActivityWrapper(
    val activity: AppCompatActivity,
    val fragmentHostId: Int?,
)
