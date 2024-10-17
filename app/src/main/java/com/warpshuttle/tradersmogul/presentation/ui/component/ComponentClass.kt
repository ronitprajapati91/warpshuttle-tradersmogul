package com.warpshuttle.tradersmogul.presentation.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.warpshuttle.tradersmogul.R
import com.warpshuttle.tradersmogul.presentation.ui.theme.AppTheme


object ComponentClass {
    private fun Modifier.bounceClickEffect() = composed {
        var isPressed by remember { mutableStateOf(false) }
        val scale by animateFloatAsState(if (isPressed) 0.70f else 1f, label = "")

        this
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .pointerInput(isPressed) {
                awaitPointerEventScope {
                    isPressed = if (isPressed) {
                        waitForUpOrCancellation()
                        false
                    } else {
                        awaitFirstDown(false)
                        true
                    }
                }
            }
    }

    @Composable
    fun WarpButton(
        title: String,
        isValid: Boolean,
        type: String?,
        modifier: Modifier = Modifier,
        onClick: (isValid: Boolean) -> Unit
    ) {
        val hapticFeedback = LocalHapticFeedback.current

        val buttonColors =
            if (type == "bluishType") AppTheme.colors.colorDark else if (type == "greyishType") AppTheme.colors.colorDark else AppTheme.colors.colorDark

        val buttonTextColors =
            if (type == "bluishType") AppTheme.colors.colorWhite else if (type == "greyishType") AppTheme.colors.colorWhite else AppTheme.colors.colorWhite

        val borderColors =
            if (type == "bluishType") AppTheme.colors.colorDark else if (type == "greyishType") AppTheme.colors.colorDark else AppTheme.colors.colorDark

        Button(
            modifier = modifier
                .bounceClickEffect()
                .fillMaxWidth()
                .height(AppTheme.dimens.dimens50)
                .border(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(
                        listOf(
                            borderColors,
                            borderColors
                        )
                    ),
                    shape = RoundedCornerShape(AppTheme.dimens.dimens12)
                ),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.textButtonColors(buttonColors),
            onClick = {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                onClick(isValid)
            }) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (type == "transparencyType") {
                    Icon(
                        modifier = Modifier.padding(end = AppTheme.dimens.dimens12),
                        imageVector = Icons.Default.DateRange,
                        tint = buttonTextColors,
                        contentDescription = "uploadImage"
                    )
                }

                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.text18Bold.copy(buttonTextColors)
                )
            }
        }
    }

    @Composable
    fun WarpTextField(
        modifier: Modifier = Modifier,
        defaultValue: String,
        placeholderText: String,
        placeHolderColor: Color = AppTheme.colors.colorPlaceHolderText,
        buttonOffsetX: Dp,
        textLength: Int = Int.MAX_VALUE,
        keyboardOptions: KeyboardOptions,
        onValueChange: (String) -> Unit
    ) {
        val focusManager = LocalFocusManager.current

        TextField(
            value = defaultValue,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .offset(x = buttonOffsetX, y = 0.dp)
                .border(
                    width = 1.dp,
                    brush = Brush.horizontalGradient(
                        listOf(
                            AppTheme.colors.colorLightGrey,
                            AppTheme.colors.colorLightGrey
                        )
                    ),
                    shape = RoundedCornerShape(AppTheme.dimens.dimens12)
                ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = AppTheme.colors.colorPrimaryText,
                backgroundColor = colorResource(
                    id = R.color.white
                ),
                cursorColor = AppTheme.colors.colorDark,
                disabledIndicatorColor = AppTheme.colors.colorDark,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                androidx.compose.material.Text(
                    placeholderText,
                    style = AppTheme.typography.text16Regular.copy(placeHolderColor),
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            maxLines = 1,
            shape = RoundedCornerShape(AppTheme.dimens.dimens16),
            textStyle = AppTheme.typography.text16Regular.copy(
                textAlign = TextAlign.Start,
                color = AppTheme.colors.colorPrimaryText
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
        )
    }

//    @Composable
//    fun ImageItem(
//        modifier: Modifier,
//        data: Any?,
//        crossfadeValue: Int = 300,
//        contentDescription: String? = null,
//        contentScale: ContentScale = ContentScale.Crop
//    ) {
//        coil.compose.AsyncImage(
//            modifier = modifier,
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(data)
//                .crossfade(crossfadeValue)
//                .build(),
//            contentDescription = contentDescription,
//            contentScale = contentScale
//        )
//    }
}

