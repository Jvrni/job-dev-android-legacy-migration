package com.goomer.menu.details.contract

import com.goomer.common.base.UnidirectionalViewModel
import com.goomer.domain.models.Menu

interface MenuDetailContract : UnidirectionalViewModel<MenuDetailContract.State, MenuDetailContract.Event, MenuDetailContract.Effect> {

    data class State(val menu: Menu? = null)

    sealed class Event {
        data class OnStart(val menu: Menu) : Event()
        data object OnBack : Event()
    }

    sealed class Effect {
        data object OnBack : Effect()
    }
}