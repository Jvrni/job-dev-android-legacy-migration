package com.goomer.ps

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.goomer.designsystem.theme.MenuTheme
import com.goomer.menu.menuGraph
import com.goomer.navigation.Destinations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainNavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MenuTheme {
                NavHost(
                    navController = navController,
                    startDestination = Destinations.MenuList
                ) {
                    menuGraph(navController)
                }
            }
        }
    }
}