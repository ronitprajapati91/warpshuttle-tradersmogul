package com.warpshuttle.tradersmogul.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object Splash : Screen("splash_screen")
    data object Intro: Screen("intro_screen")
    data object SignUp : Screen("signUp_screen")
    data object SignIn : Screen("signIn_screen")
    data object ForgotPassword : Screen("forgot_password_screen")
    data object EnterOtp : Screen("otp_screen")
    data object ChangePassword : Screen("change_password_screen")

    data object Dashboard : Screen("dashboard_screen")

    data object Home : NavigationItem(
        route = "home",
        label = "Home",
        icon = Icons.Rounded.Home
    )

    data object Team : NavigationItem(
        route = "tasks",
        label = "Team",
        icon = Icons.Rounded.AccountBox
    )

    data object Search : NavigationItem(
        route = "search",
        label = "Search",
        icon = Icons.Rounded.Search
    )

    data object Blog : NavigationItem(
        route = "blog",
        label = "Blog",
        icon = Icons.Rounded.DateRange
    )

    data object Alert : NavigationItem(
        route = "alert",
        label = "Alert",
        icon = Icons.Rounded.Notifications
    )

    data object Profile : Screen("profile_screen")

}

sealed class NavigationItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)