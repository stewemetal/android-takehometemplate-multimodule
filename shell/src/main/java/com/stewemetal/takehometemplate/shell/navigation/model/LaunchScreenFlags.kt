package com.stewemetal.takehometemplate.shell.navigation.model

/**
 * A wrapper for Intent flags.
 */
enum class LaunchScreenFlags {
    /**
     * NO_DUPLICATE_ON_TOP: A - B (launch B) -> A - B
     */
    NO_DUPLICATE_ON_TOP,

    /**
     * BACK_ON_LAST_INSTANCE_AND_CLEAR_BACKSTACK: A - B - C (launch B) -> A - B
     *
     */
    BACK_ON_LAST_INSTANCE_AND_CLEAR_BACKSTACK,

    /**
     * CLEAR_WHOLE_BACKSTACK: A - B (launch C) -> C
     *
     */
    CLEAR_WHOLE_BACKSTACK,
}
