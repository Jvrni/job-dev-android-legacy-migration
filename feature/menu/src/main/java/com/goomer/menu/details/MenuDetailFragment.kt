package com.goomer.menu.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.goomer.designsystem.theme.MenuTheme

class MenuDetailFragment: Fragment() {

    private val args by navArgs<MenuDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MenuTheme {
                MenuDetailScreen(args.data)
            }
        }
    }
}