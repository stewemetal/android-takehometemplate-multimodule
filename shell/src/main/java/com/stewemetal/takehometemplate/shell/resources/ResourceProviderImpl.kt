package com.stewemetal.takehometemplate.shell.resources

import android.content.Context
import androidx.annotation.StringRes
import org.koin.core.annotation.Singleton
import java.util.Locale

@Singleton
internal class ResourceProviderImpl(
    private val context: Context,
) : ResourceProvider {

    fun getString(@StringRes stringResId: Int): String =
        context.getString(stringResId)

    @Suppress("SpreadOperator")
    fun getString(@StringRes stringResId: Int, vararg arguments: Any): String =
        if (arguments.isNotEmpty()) {
            context.getString(stringResId, *arguments)
        } else {
            context.getString(stringResId)
        }

    fun currentLocale(): Locale {
        return context.resources.configuration.locales.get(0)
    }
}
