package com.gdg.zealicon2k25.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gdg.zealicon2k25.presentation.viewmodels.AuthViewModel

@Composable
fun RootNavGraph(navController: NavHostController ) {
    val authViewModel : AuthViewModel= hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Onboarding.route ,
    ) {
        onboardingNavGraph(navController,authViewModel )
        paymentNavGraph(navController)
        mainNavGraph(navController)
    }
}

sealed class NavRoutes(val route: String) {
    data object Onboarding : NavRoutes("onboarding_graph")
    data object Payment : NavRoutes("payment_graph")
    data object Main : NavRoutes("main_graph")
}