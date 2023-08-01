package com.stewemetal.takehometemplate.shell.navigation

import androidx.annotation.IdRes

interface FragmentNavHostIdProvider {
    /**
     * This function provides the ID of the Fragment Host to the navigation SDK, that will be used
     * for Fragment navigation
     */
    @IdRes
    fun getFragmentNavHostId(): Int? = null
}
