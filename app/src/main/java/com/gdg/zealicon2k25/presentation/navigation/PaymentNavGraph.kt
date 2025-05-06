package com.gdg.zealicon2k25.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gdg.zealicon2k25.presentation.ui.PurchaseZealScreen

fun NavGraphBuilder.paymentNavGraph(navHostController: NavHostController){
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
                }
            )
        }
    }
}

sealed class Payment(val route: String){
    data object PaymentScreen: Auth("payment_screen")
}