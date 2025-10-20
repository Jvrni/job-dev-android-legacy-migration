package com.goomer.menu.list.contract

import com.goomer.common.base.UnidirectionalViewModel
import com.goomer.data.models.Menu

interface MenuListContract :
    UnidirectionalViewModel<MenuListContract.State, MenuListContract.Event, MenuListContract.Effect> {

    data class State(
        val list: List<Menu> = emptyList(),
        val showError: Boolean = false,
        val showLoading: Boolean = true
    )

    sealed class Event {
        data object OnStart : Event()
        data class OnNavigate(val menu: Menu) : Event()
    }

    sealed class Effect {
        data class NavigateToDetail(val menu: Menu) : Effect()
    }
}