package com.goomer.designsystem.theme.custom

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
class Dimensions(
    val xxxlarge_padding: Dp,
    val xxlarge_padding: Dp,
    val xlarge_padding: Dp,
    val large_padding: Dp,
    val medium_padding: Dp,
    val smedium_padding: Dp,
    val small_padding: Dp,
    val xsmall_padding: Dp,
    val xxsmall_padding: Dp,
    val small_corner_radius: Dp,
    val regular_corner_radius: Dp,
    val medium_corner_radius: Dp,
    val big_corner_radius: Dp,
    val extra_big_corner_radius: Dp,
    val biggest_corner_radius: Dp,
    val extra_biggest_corner_radius: Dp,
    val bigger_biggest_corner_radius: Dp,
    val divider_stroke: Dp,
    val medium_stroke: Dp,
    val regular_stroke: Dp,
)

val DefaultDimensions = Dimensions(
    xxxlarge_padding = 64.dp,
    xxlarge_padding = 48.dp,
    xlarge_padding = 32.dp,
    large_padding = 24.dp,
    medium_padding = 16.dp,
    smedium_padding = 12.dp,
    small_padding = 8.dp,
    xsmall_padding = 6.dp,
    xxsmall_padding = 4.dp,
    small_corner_radius = 4.dp,
    regular_corner_radius = 5.dp,
    medium_corner_radius = 8.dp,
    big_corner_radius = 10.dp,
    extra_big_corner_radius = 12.dp,
    biggest_corner_radius = 16.dp,
    extra_biggest_corner_radius = 20.dp,
    bigger_biggest_corner_radius = 24.dp,
    divider_stroke = 0.5.dp,
    medium_stroke = 1.dp,
    regular_stroke = 2.dp,
)