package com.gdg.zealicon2k25.presentation.navigation

import android.app.Activity
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gdg.zealicon2k25.presentation.ui.PurchaseZealScreen
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel

fun NavGraphBuilder.paymentNavGraph(navHostController: NavHostController, activity: Activity, paymentViewModel: PaymentViewModel){
    navigation(
        route = NavRoutes.Payment.route,
        startDestination = Payment.PaymentScreen.route
    ){
        composable(route = Payment.PaymentScreen.route){
            PurchaseZealScreen(
                continueWithoutZealOnClick = {
                    navHostController.navigate(NavRoutes.Main.route){
                        popUpTo(NavRoutes.Payment.route)
                    }
                },
                homeOnClick = {
                    navHostController.navigate(NavRoutes.Main.route){
                        popUpTo(NavRoutes.Payment.route)
                    }
                },
                activity = activity,
                paymentViewModel = paymentViewModel
            )
        }
    }
}

sealed class Payment(val route: String){
    data object PaymentScreen: Auth("payment_screen")
}