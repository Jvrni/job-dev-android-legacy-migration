package com.goomer.menu.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.findNavController
import com.goomer.common.base.collectInLaunchedEffect
import com.goomer.common.base.use
import com.goomer.designsystem.theme.MenuTheme
import com.goomer.menu.details.MenuDetailFragmentArgs
import com.goomer.menu.list.contract.MenuListContract
import com.goomer.navigation.R
import com.goomer.navigation.safeNavigate

class MenuListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MenuTheme {
                val viewModel: MenuListViewModel = hiltViewModel()
                val (state, event, effect) = use(viewModel = viewModel)

                LaunchedEffect(Unit) {
                    event.invoke(MenuListContract.Event.OnStart)
                }

                effect.collectInLaunchedEffect { dispatch ->
                    when (dispatch) {
                        is MenuListContract.Effect.NavigateToDetail -> {
                            findNavController().safeNavigate(
                                com.goomer.menu.R.id.menuDetailFragment,
                                MenuDetailFragmentArgs(dispatch.menu).toBundle()
                            )
                        }
                    }
                }

                MenuListScreen(state, event)
            }
        }
    }
}