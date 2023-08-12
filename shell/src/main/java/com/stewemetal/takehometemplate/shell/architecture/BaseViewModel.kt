package com.stewemetal.takehometemplate.shell.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

@Suppress("Unused", "MemberVisibilityCanBePrivate")
abstract class BaseViewModel<ViewEvent : Any, State : Any>(
    initialState: State,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val viewEvent = MutableSharedFlow<ViewEvent>()

    init {
        viewModelScope.launch {
            viewEvent
                .distinctUntilChanged()
                .collect { event ->
                    onViewEvent(event)
                }
        }
    }

    fun triggerViewEvent(event: ViewEvent) {
        viewModelScope.launch {
            viewEvent.emit(event)
        }
    }

    protected fun emitNewState(transformState: State.() -> State) {
        _state.getAndUpdate { oldState ->
            transformState(oldState)
        }
    }

    protected abstract fun onViewEvent(event: ViewEvent)
}
