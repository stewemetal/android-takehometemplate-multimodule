package com.stewemetal.takehometemplate.shell.architecture

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {

    val main: CoroutineContext
    val io: CoroutineContext
    val computation: CoroutineContext
}

internal class DefaultDispatcherProvider : DispatcherProvider {
    override val main: CoroutineContext = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.IO
    override val computation: CoroutineContext = Dispatchers.Unconfined
}

internal class TestDispatcherProvider : DispatcherProvider {
    override val main: CoroutineContext = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.Main
    override val computation: CoroutineContext = Dispatchers.Main
}
