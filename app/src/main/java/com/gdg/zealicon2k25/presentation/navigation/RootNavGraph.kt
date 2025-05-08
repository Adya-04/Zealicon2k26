package com.gdg.zealicon2k25.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gdg.zealicon2k25.presentation.ui.viewmodels.ImageUploadViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.EventsViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.MerchViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootNavGraph(navController: NavHostController , startDestination: String) {
    val authViewModel : AuthViewModel= hiltViewModel()
    val imageViewModel : ImageUploadViewModel = hiltViewModel()
    val eventsViewModel : EventsViewModel = hiltViewModel()
    val merchViewModel : MerchViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination ,
    ) {
        onboardingNavGraph(navController,authViewModel, imageViewModel )
        paymentNavGraph(navController)
        mainNavGraph(navController  , eventsViewModel , authViewModel , merchViewModel)
    }
}

sealed class NavRoutes(val route: String) {
    data object Onboarding : NavRoutes("onboarding_graph")
    data object Payment : NavRoutes("payment_graph")
    data object Main : NavRoutes("main_graph")
}