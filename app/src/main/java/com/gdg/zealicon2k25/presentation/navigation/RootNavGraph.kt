package com.gdg.zealicon2k25.presentation.navigation

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gdg.zealicon2k25.presentation.ui.SplashScreen
import com.gdg.zealicon2k25.presentation.ui.viewmodels.ImageUploadViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.EventsViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.MerchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavGraph(navController: NavHostController, activity: Activity, paymentViewModel: PaymentViewModel) {
    val authViewModel : AuthViewModel= hiltViewModel()
    val imageViewModel : ImageUploadViewModel = hiltViewModel()
    val eventsViewModel : EventsViewModel = hiltViewModel()
    val merchViewModel : MerchViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.SplashScreen.route,
    ) {
        composable(NavRoutes.SplashScreen.route) {
            SplashScreen(
                authViewModel = authViewModel,
                navigateToHome = {
                    navController.navigate(NavRoutes.Main.route) {
                        popUpTo(NavRoutes.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                },
                navigateToWelcome = {
                    navController.navigate(NavRoutes.Onboarding.route) {
                        popUpTo(NavRoutes.SplashScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        onboardingNavGraph(navController,authViewModel, imageViewModel , paymentViewModel)
        paymentNavGraph(navController, activity, paymentViewModel)
        mainNavGraph(navController  , eventsViewModel , authViewModel , paymentViewModel, merchViewModel, activity)
    }
}

sealed class NavRoutes(val route: String) {
    data object Onboarding : NavRoutes("onboarding_graph")
    data object Payment : NavRoutes("payment_graph")
    data object Main : NavRoutes("main_graph")
    data object SplashScreen : NavRoutes("splash_screen")
}