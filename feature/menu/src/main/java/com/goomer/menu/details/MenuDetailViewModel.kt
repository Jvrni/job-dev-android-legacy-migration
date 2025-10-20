package com.goomer.menu.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goomer.menu.details.contract.MenuDetailContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel @Inject constructor() : ViewModel(), MenuDetailContract {
    private val _state: MutableStateFlow<MenuDetailContract.State> = MutableStateFlow(MenuDetailContract.State())
    override val state: StateFlow<MenuDetailContract.State> = _state.asStateFlow()

    private val _effect: MutableSharedFlow<MenuDetailContract.Effect> = MutableSharedFlow()
    override val effect: SharedFlow<MenuDetailContract.Effect> = _effect.asSharedFlow()


    override fun event(event: MenuDetailContract.Event) {
        when (event) {
            is MenuDetailContract.Event.OnStart -> {
                _state.value = MenuDetailContract.State(event.menu)
            }

            is MenuDetailContract.Event.OnBack -> {
                viewModelScope.launch {
                    _effect.emit(MenuDetailContract.Effect.OnBack)
                }
            }
        }
    }
}