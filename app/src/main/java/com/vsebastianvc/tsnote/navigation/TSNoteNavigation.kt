package com.vsebastianvc.tsnote.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vsebastianvc.tsnote.screen.splash.SplashScreen
import com.vsebastianvc.tsnote.screen.main.MainScreen


@Composable
fun TSNoteNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = TSNoteScreens.SplashScreen.name
    ) {
        composable(TSNoteScreens.SplashScreen.name) {
            SplashScreen(navController = navController)

        }

        composable(TSNoteScreens.MainScreen.name) {
            MainScreen()
        }


    }
}