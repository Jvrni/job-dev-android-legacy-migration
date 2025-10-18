package com.goomer.ps.feature.menu.list.state

import com.goomer.ps.MenuItem

data class MenuListState(
    val list: List<MenuItem> = emptyList(),
)