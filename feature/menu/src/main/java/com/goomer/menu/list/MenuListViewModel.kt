package com.goomer.menu.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goomer.data.MenuRepository
import com.goomer.menu.list.contract.MenuListContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MenuListViewModel @Inject constructor(
    private val repository: MenuRepository,
) : ViewModel(), MenuListContract {

    private val _state = MutableStateFlow(MenuListContract.State())
    override val state: StateFlow<MenuListContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<MenuListContract.Effect>()
    override val effect: SharedFlow<MenuListContract.Effect> = _effect.asSharedFlow()

    override fun event(event: MenuListContract.Event) {
        when (event) {
            is MenuListContract.Event.OnStart -> onStart()
            is MenuListContract.Event.OnNavigate -> viewModelScope.launch {
                _effect.emit(MenuListContract.Effect.NavigateToDetail(event.menu))
            }
        }
    }

    private fun onStart() {
        viewModelScope.launch {
            repository.getList()
                .catch {
                    _state.update {
                        it.copy(
                            showError = true,
                            showLoading = false,
                            list = emptyList()
                        )
                    }
                }.collect { list ->
                    _state.update {
                        it.copy(
                            showError = false,
                            showLoading = false,
                            list = list
                        )
                    }
                }
        }
    }
}