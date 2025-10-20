package com.goomer.menu

import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.goomer.common.base.collectInLaunchedEffect
import com.goomer.common.base.use
import com.goomer.designsystem.theme.MenuTheme
import com.goomer.menu.details.MenuDetailScreen
import com.goomer.menu.details.MenuDetailViewModel
import com.goomer.menu.details.contract.MenuDetailContract
import com.goomer.menu.list.MenuListScreen
import com.goomer.menu.list.MenuListViewModel
import com.goomer.menu.list.contract.MenuListContract
import com.goomer.navigation.Destinations
import com.goomer.navigation.composable

fun NavGraphBuilder.menuGraph(navController: NavController) {
    composable<Destinations.MenuList> {
        val viewModel: MenuListViewModel = hiltViewModel()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(MenuListContract.Event.OnStart)
        }

        effect.collectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is MenuListContract.Effect.NavigateToDetail -> {
                    navController.navigate(Destinations.MenuDetail(dispatch.menu))
                }
            }
        }

        MenuTheme {
            MenuListScreen(state, event)
        }
    }

    composable<Destinations.MenuDetail>(typeMap = Destinations.MenuDetail.typeMap) {
        val viewModel: MenuDetailViewModel = hiltViewModel()
        val (state, event, effect) = use(viewModel = viewModel)

        LaunchedEffect(Unit) {
            event.invoke(MenuDetailContract.Event.OnStart)
        }

        effect.collectInLaunchedEffect { dispatch ->
            when (dispatch) {
                is MenuDetailContract.Effect.OnBack -> {
                    navController.popBackStack()
                }
            }
        }

        MenuTheme {
            MenuDetailScreen(state, event)
        }
    }
}