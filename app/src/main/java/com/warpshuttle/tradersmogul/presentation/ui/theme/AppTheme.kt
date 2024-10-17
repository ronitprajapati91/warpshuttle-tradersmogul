package com.warpshuttle.tradersmogul.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val DarkColorPalette = darkColors(
    primary = ColorPrimary,
    secondary = ColorSecondary
)

private val LightColorPalette = lightColors(
    primary = ColorPrimary,
    secondary = ColorSecondary

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

private val LocalAppTypography = staticCompositionLocalOf { textSmallDimension }

object AppTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val materialColors: Colors
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colors

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val dimens: AppDimensions
        @Composable
        get() = LocalAppDimens.current

}

@Composable
fun AnimatedSplashScreenTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current

    val typography =
        if (configuration.screenWidthDp <= 320) textSmallDimension else textSw420Dimensions
    val dimensions = if (configuration.screenWidthDp <= 320) smallDimension else sw420Dimension
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.Black.copy(alpha = 0.2f)
    )

    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    ProvideDimens(dimensions = dimensions) {
        ProvideAppTypography(typography = typography) {
            MaterialTheme(
                colors = colors,
                typography = androidx.compose.material.Typography(),
                content = content
            )
        }
    }
}

@Composable
fun ProvideDimens(dimensions: AppDimensions, content: @Composable () -> Unit) {
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(LocalAppDimens provides dimensionSet, content = content)
}

private val LocalAppDimens = staticCompositionLocalOf { smallDimension }

@Composable
fun ProvideAppTypography(typography: AppTypography, content: @Composable () -> Unit) {
    val typographySet = remember { typography }
    CompositionLocalProvider(LocalAppTypography provides typographySet, content = content)
}
