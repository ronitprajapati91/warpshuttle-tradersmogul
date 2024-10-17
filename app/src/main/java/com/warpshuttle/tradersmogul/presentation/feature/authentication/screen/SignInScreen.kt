package com.warpshuttle.tradersmogul.presentation.feature.authentication.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.warpshuttle.tradersmogul.R
import com.warpshuttle.tradersmogul.presentation.ui.component.ComponentClass
import com.warpshuttle.tradersmogul.presentation.ui.theme.AppTheme
import kotlinx.coroutines.launch


@Composable
fun SignInScreen(
    phoneNumber: String,
    password: String,
    onPhoneNumberValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onDoNotHaveAccountClicked: () -> Unit,
    onSignInClicked: () -> Unit
) {

    var phoneOffsetX by remember {
        mutableFloatStateOf(0f)
    }

    var passwordOffsetX by remember {
        mutableFloatStateOf(0f)
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.colorWhite)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .weight(0.37f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "appIcon",
                colorFilter = ColorFilter.tint(AppTheme.colors.colorPrimary),
                modifier = Modifier
                    .height(AppTheme.dimens.dimens80)
                    .width(AppTheme.dimens.dimens80)
            )

            Text(
                text = "Sign In",
                textAlign = TextAlign.Center,
                style = AppTheme.typography.text30Bold.copy(AppTheme.colors.colorPrimaryText),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.63f)
                .clip(
                    shape = RoundedCornerShape(
                        AppTheme.dimens.dimens16,
                        AppTheme.dimens.dimens16
                    )
                )
                .padding(
                    start = AppTheme.dimens.dimens20,
                    top = AppTheme.dimens.dimens40,
                    end = AppTheme.dimens.dimens20,
                )
        ) {
            ComponentClass.WarpTextField(
                modifier = Modifier.padding(bottom = AppTheme.dimens.dimens12),
                defaultValue = phoneNumber,
                placeholderText = "Enter Number",
                placeHolderColor = AppTheme.colors.colorPlaceHolderText,
                buttonOffsetX = phoneOffsetX.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                ),
                onValueChange = onPhoneNumberValueChange
            )

            ComponentClass.WarpTextField(
                modifier = Modifier.padding(bottom = AppTheme.dimens.dimens12),
                defaultValue = password,
                placeholderText = "Enter Password",
                placeHolderColor = AppTheme.colors.colorPlaceHolderText,
                buttonOffsetX = passwordOffsetX.dp,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                ),
                onValueChange = onPasswordValueChange
            )

            Text(
                text = "Forgot Password",
                textAlign = TextAlign.End,
                style = AppTheme.typography.text16Bold.copy(AppTheme.colors.colorPrimaryText),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = AppTheme.dimens.dimens12)
                    .clickable {
                        onForgotPasswordClicked()
                    }
            )

            ComponentClass.WarpButton(
                title = "Sign In",
                isValid = false,
                type = "bluishType",
                modifier = Modifier.padding(top = AppTheme.dimens.dimens20)
            ) {
                if (phoneNumber.isEmpty()) {
                    coroutineScope.launch {
                        animate(
                            initialValue = -5f,
                            targetValue = 5f,
                            animationSpec = repeatable(
                                4,
                                repeatMode = RepeatMode.Reverse,
                                animation = tween(80, easing = LinearEasing)
                            )
                        ) { value, _ ->
                            phoneOffsetX = value
                        }
                    }
                } else if (password.isEmpty()) {
                    coroutineScope.launch {
                        animate(
                            initialValue = -5f,
                            targetValue = 5f,
                            animationSpec = repeatable(
                                4,
                                repeatMode = RepeatMode.Reverse,
                                animation = tween(80, easing = LinearEasing)
                            )
                        ) { value, _ ->
                            passwordOffsetX = value
                        }
                    }
                } else {
                    onSignInClicked()
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppTheme.dimens.dimens30),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Do not have an account? ",
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.text16Regular.copy(AppTheme.colors.colorSecondaryText),
                    modifier = Modifier
                        .wrapContentWidth()
                )
                Text(
                    text = "Sign Up",
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.text16Bold.copy(AppTheme.colors.colorPrimaryText),
                    modifier = Modifier
                        .wrapContentWidth()
                        .clickable {
                            onDoNotHaveAccountClicked()
                        }
                )
            }
        }
    }
}