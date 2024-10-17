package com.warpshuttle.tradersmogul.presentation.feature.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.warpshuttle.tradersmogul.presentation.ui.theme.AppTheme
import kotlinx.coroutines.delay
import com.warpshuttle.tradersmogul.R

@Composable
fun SplashScreen(onSplashEnd : () -> Unit){
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2500,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(500L)
        onSplashEnd()
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(AppTheme.colors.colorPrimary)) {
        Image(painter = painterResource(id = R.mipmap.ic_launcher),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value).size(height = 100.dp, width = 100.dp))
    }

}