package com.gdg.zealicon2k25.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Onboarding.route
    ) {
        onboardingNavGraph(navController)
        paymentNavGraph(navController)
        mainNavGraph(navController)
    }
}

sealed class NavRoutes(val route: String) {
    data object Onboarding : NavRoutes("onboarding_graph")
    data object Payment : NavRoutes("payment_graph")
    data object Main : NavRoutes("main_graph")
}