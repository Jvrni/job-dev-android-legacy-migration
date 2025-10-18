package com.goomer.ps.navigation

import android.content.Context
import android.content.Intent
import com.goomer.data.models.Menu
import com.goomer.ps.feature.menu.details.MenuDetailActivity
import javax.inject.Inject

class MenuNavigatorImpl @Inject constructor() : MenuNavigator {
    override fun navigateToDetail(
        context: Context,
        menu: Menu
    ) {
        val intent = Intent(context, MenuDetailActivity::class.java)
        intent.putExtra(MenuDetailActivity.EXTRA_MENU, menu)
        context.startActivity(intent)
    }
}
