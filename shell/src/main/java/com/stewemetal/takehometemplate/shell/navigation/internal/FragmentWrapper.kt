package com.stewemetal.takehometemplate.shell.navigation.internal

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.stewemetal.takehometemplate.shell.navigation.model.ForwardBackTransitions

internal data class FragmentWrapper(
    val activityType: Class<out Activity>,
    val fragmentType: Class<out Fragment>,
    val extras: Bundle?,
    val transition: ForwardBackTransitions?,
)
