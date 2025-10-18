package com.goomer.ps.navigation

import android.content.Context
import com.goomer.data.models.Menu

interface MenuNavigator {
    fun navigateToDetail(context: Context, menu: Menu)
}