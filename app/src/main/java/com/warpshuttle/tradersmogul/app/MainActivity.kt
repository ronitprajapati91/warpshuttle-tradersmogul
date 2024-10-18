package com.warpshuttle.tradersmogul.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.warpshuttle.tradersmogul.navigation.Screen
import com.warpshuttle.tradersmogul.navigation.customAnimatedComposable
import com.warpshuttle.tradersmogul.navigation.rememberCustomAnimatedNavController
import com.warpshuttle.tradersmogul.presentation.feature.authentication.screen.ChangePasswordScreen
import com.warpshuttle.tradersmogul.presentation.feature.authentication.screen.EnterOtpScreen
import com.warpshuttle.tradersmogul.presentation.feature.authentication.screen.ForgotPasswordScreen
import com.warpshuttle.tradersmogul.presentation.feature.authentication.screen.SignInScreen
import com.warpshuttle.tradersmogul.presentation.feature.authentication.screen.SignUpScreen
import com.warpshuttle.tradersmogul.presentation.feature.splashscreen.SplashScreen
import com.warpshuttle.tradersmogul.presentation.ui.theme.AnimatedSplashScreenTheme

class MainActivity : ComponentActivity() {
    private var navController: NavHostController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AnimatedSplashScreenTheme {
                navController = rememberCustomAnimatedNavController()
                SetupNavGraph(navController = navController!!)
            }
        }
    }

    @Composable
    fun SetupNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route
        ) {
            customAnimatedComposable(route = Screen.Splash.route) {
                SplashScreen {
                    navController.navigator(
                        Screen.SignUp.route, clearBackStack = true
                    )
                }
            }

            customAnimatedComposable(route = Screen.SignUp.route) {
                SignUpScreen(
                    phoneNumber = "",
                    password = "",
                    confirmPassword = "",
                    onPhoneNumberValueChange = {},
                    onPasswordValueChange = {},
                    onConfirmPasswordValueChange = {},
                    onAlreadyHaveAccountClicked = {
                        navController.navigator(Screen.SignIn.route, clearBackStack = false)
                    }) {

                }
            }

            customAnimatedComposable(route = Screen.SignIn.route) {
                SignInScreen(
                    phoneNumber = "",
                    password = "",
                    onPhoneNumberValueChange = {},
                    onPasswordValueChange = {},
                    onForgotPasswordClicked = {},
                    onDoNotHaveAccountClicked = {
                        navController.navigator(
                            Screen.SignUp.route,
                            clearBackStack = true
                        )
                    }) {

                }
            }

            customAnimatedComposable(route = Screen.ForgotPassword.route){
                ForgotPasswordScreen(phoneNumber = "", onPhoneNumberValueChange = {}) {

                }
            }

            customAnimatedComposable(route = Screen.EnterOtp.route){
                EnterOtpScreen(otpNumber = "", onOtpNumberValueChange = {}) {

                }
            }

            customAnimatedComposable(route = Screen.ChangePassword.route){
                ChangePasswordScreen(
                    password = "",
                    confirmPassword = "",
                    onPasswordValueChange = {},
                    onConfirmPasswordValueChange = {}
                ) {

                }
            }
        }
    }

    private fun NavController.navigator(route: String, clearBackStack: Boolean = false) {
        if (clearBackStack) {
            this.popBackStack()
        }
        navigate(route) {
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

