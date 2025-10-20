package com.goomer.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import com.goomer.designsystem.theme.custom.DefaultDimensions
import com.goomer.designsystem.theme.custom.Dimensions
import com.goomer.designsystem.theme.custom.MenuColors
import com.goomer.designsystem.theme.custom.darkThemeColors
import com.goomer.designsystem.theme.custom.lightThemeColors

private val LocalColors = staticCompositionLocalOf<MenuColors> { error("No Colors provided") }
private val LocalDimens = staticCompositionLocalOf<Dimensions> { error("No Dimens provided") }

val Colors: MenuColors
    @Composable
    get() = LocalColors.current

val Dimens: Dimensions
    @Composable
    get() = LocalDimens.current


@Composable
fun MenuTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) darkThemeColors else lightThemeColors
    val rememberedColors = remember { colors.copy() }.apply { update(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalDimens provides DefaultDimensions
    ) {
        content.invoke()
    }
}