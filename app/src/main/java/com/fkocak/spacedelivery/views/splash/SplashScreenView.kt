package com.fkocak.spacedelivery.views.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.fkocak.spacedelivery.R
import com.fkocak.spacedelivery.ui.theme.BACKGROUND_HALF_WHITE
import kotlinx.coroutines.delay

@Composable
fun SplashScreenView(
    onFinishSplashScreen: () -> Unit
){

    val img = painterResource(id = R.drawable.ic_space_logo)

    val scale = remember {
        Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f).getInterpolation(it)
                })
        )
        delay(2000L)
        onFinishSplashScreen.invoke()
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(BACKGROUND_HALF_WHITE)
    ) {

        Image(
            painter = img,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .scale(scale.value)
        )

    }

}