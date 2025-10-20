package com.goomer.menu.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.goomer.common.base.collectInLaunchedEffect
import com.goomer.common.base.use
import com.goomer.designsystem.theme.MenuTheme
import com.goomer.menu.details.contract.MenuDetailContract

class MenuDetailFragment: Fragment() {

    private val args by navArgs<MenuDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val viewModel: MenuDetailViewModel = hiltViewModel()
            val (state, event, effect) = use(viewModel = viewModel)

            LaunchedEffect(Unit) {
                event.invoke(MenuDetailContract.Event.OnStart(args.data))
            }

            effect.collectInLaunchedEffect { dispatch ->
                when (dispatch) {
                    is MenuDetailContract.Effect.OnBack -> {
                        findNavController().popBackStack()
                    }
                }
            }

            MenuTheme {
                MenuDetailScreen(state, event)
            }
        }
    }
}