package com.warpshuttle.tradersmogul.presentation.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
    primary: Color,
    secondary: Color,
    textPrimary: Color,
    textSecondary: Color,
    background: Color,
    onSurface: Color,
    error: Color,
    isLight: Boolean
) {
    private var primary by mutableStateOf(primary)
    private var secondary by mutableStateOf(secondary)
    private var textSecondary by mutableStateOf(textSecondary)
    private var textPrimary by mutableStateOf(textPrimary)
    private var error by mutableStateOf(error)
    private var background by mutableStateOf(background)
    private var onSurface by mutableStateOf(onSurface)
    private var isLight by mutableStateOf(isLight)

    val colorPrimary = Color(0xFFF9D94D)
    val colorSecondary = Color(0xFFBE9B00)
    val colorError = Color(0XFFE10E0E)

    val colorDark = Color (0XFF111928)
    val colorWhite = Color(0xFFFFFFFF)
    val colorGrey = Color(0XFF454545)
    val colorLightGrey = Color(0XFFEDEFF5)

    val colorPrimaryText = Color (0XFF111928)
    val colorSecondaryText = Color (0XFFAFAFB1)
    val colorPlaceHolderText = Color(0XFF939AAD)

    fun copy(
        primary: Color = this.primary,
        secondary: Color = this.secondary,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary,
        error: Color = this.error,
        background: Color = this.background,
        onSurface: Color = this.onSurface,
        isLight: Boolean = this.isLight
    ): AppColors = AppColors(
        primary,
        secondary,
        textPrimary,
        textSecondary,
        error,
        background,
        onSurface,
        isLight
    )

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        secondary = other.secondary
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        background = other.background
        onSurface = other.onSurface
        error = other.error
    }
}

val ColorPrimary = Color(0xFFF9D94D)
val ColorSecondary = Color(0xFFBE9B00)
val ColorError = Color(0XFFE10E0E)

val ColorWhite = Color(0xFFFFFFFF)
val ColorDark = Color(0xFF111928)
val ColorGrey = Color(0XFF454545)

val ColorPrimaryText = Color (0XFF111928)
val ColorSecondaryText = Color (0XFFAFAFB1)

private val colorLightPrimary = ColorPrimary
private val colorLightSecondary = ColorSecondary
private val colorLightTextPrimary = Color(0xFF000000)
private val colorLightTextSecondary = Color(0xFF6C727A)
private val colorLightBackground = Color(0xFFFFFFFF)
private val colorLightError = ColorError

private val colorDarkPrimary = ColorPrimary
private val colorDarkSecondary = ColorSecondary
private val colorDarkTextPrimary = Color(0xFFFAFAFA)
private val colorDarkTextSecondary = Color(0xFF6C727A)
private val colorDarkBackground = Color(0xFF090A0A)
private val colorDarkError = ColorError

fun lightColors(
    primary: Color = colorLightPrimary,
    secondary: Color = colorLightSecondary,
    textPrimary: Color = colorLightTextPrimary,
    textSecondary: Color = colorLightTextSecondary,
    background: Color = colorLightBackground,
    onSurface: Color = ColorDark,
    error: Color = colorLightError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    onSurface = onSurface,
    error = error,
    isLight = true
)

fun darkColors(
    primary: Color = colorDarkPrimary,
    secondary: Color = colorDarkSecondary,
    textPrimary: Color = colorDarkTextPrimary,
    textSecondary: Color = colorDarkTextSecondary,
    background: Color = colorDarkBackground,
    onSurface: Color = ColorWhite,
    error: Color = colorDarkError
): AppColors = AppColors(
    primary = primary,
    secondary = secondary,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    background = background,
    onSurface = onSurface,
    error = error,
    isLight = false
)

val LocalColors = staticCompositionLocalOf { lightColors() }


