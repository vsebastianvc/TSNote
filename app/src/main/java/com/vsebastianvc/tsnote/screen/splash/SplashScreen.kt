package com.vsebastianvc.tsnote.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vsebastianvc.tsnote.R
import com.vsebastianvc.tsnote.navigation.TSNoteScreens

@Composable
fun SplashScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ts_animation))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            progress = { logoAnimationState.progress }
        )
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            navController.navigate(TSNoteScreens.MainScreen.name)
        }
    }
}