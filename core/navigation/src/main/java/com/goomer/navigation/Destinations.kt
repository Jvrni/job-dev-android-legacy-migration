package com.goomer.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.goomer.domain.models.Menu
import kotlinx.serialization.Serializable
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@Serializable
sealed class Destinations {

    @Serializable
    data object MenuList : Destinations()

    @Serializable
    data class MenuDetail(val menu: Menu) : Destinations() {
        companion object {
            val typeMap = mapOf(typeOf<Menu>() to serializableType<Menu>())

            fun from(savedStateHandle: SavedStateHandle) =
                savedStateHandle.toRoute< MenuDetail>(typeMap)
        }
    }
}

inline fun <reified T : Destinations> NavGraphBuilder.composable(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    enterTransition: EnterTransition? = null,
    exitTransition: ExitTransition? = null,
    crossinline content: @Composable () -> Unit
) {
    composable<T>(
        typeMap = typeMap,
        enterTransition = { enterTransition },
        exitTransition = { exitTransition }
    ) {
        AnimatedVisibility(
            visible = true,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            content.invoke()
        }
    }
}