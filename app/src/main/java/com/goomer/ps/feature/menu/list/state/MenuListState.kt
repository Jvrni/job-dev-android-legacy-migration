package com.goomer.ps.feature.menu.list.state

import com.goomer.data.models.Menu

data class MenuListState(
    val list: List<Menu> = emptyList(),
)