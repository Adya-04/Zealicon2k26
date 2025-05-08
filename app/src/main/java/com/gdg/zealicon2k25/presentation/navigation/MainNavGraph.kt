package com.gdg.zealicon2k25.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gdg.zealicon2k25.presentation.ui.AboutUsScreen
import com.gdg.zealicon2k25.presentation.ui.ContactUsScreen
import com.gdg.zealicon2k25.presentation.ui.EntryPass
import com.gdg.zealicon2k25.presentation.ui.EventDetailScreen
import com.gdg.zealicon2k25.presentation.ui.HomeScreen
import com.gdg.zealicon2k25.presentation.ui.MenuScreen
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel

fun NavGraphBuilder.mainNavGraph(navHostController: NavHostController, paymentViewModel: PaymentViewModel){
    navigation(
        startDestination = Main.Home.routes,
        route = NavRoutes.Main.route
    ){
        composable(route = Main.Home.routes){
            HomeScreen(
                menuOnClick = {
                    navHostController.navigate(route = Main.Menu.routes)
                },
                entryPass = {
                    navHostController.navigate(route = Main.EntryPass.routes)
                },
                eventDetails = {
                    navHostController.navigate(route = Main.EventDetails.routes)
                },
                buyZealClick = {
                    navHostController.navigate(route = NavRoutes.Payment.route)
                },
                paymentViewModel = paymentViewModel
            )
        }

        composable(route = Main.Menu.routes){
            MenuScreen(
                aboutUsClick = {
                    navHostController.navigate(route = Main.AboutUs.routes)
                },
                contactUsClick = {
                    navHostController.navigate(route = Main.Contact.routes)
                },
                backOnClick = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = Main.EntryPass.routes){
            EntryPass(
                backOnClick = {
                    navHostController.popBackStack()
                },
                paymentViewModel = paymentViewModel
            )
        }

        composable(route = Main.Contact.routes){
            ContactUsScreen(
                backOnClick = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = Main.AboutUs.routes){
            AboutUsScreen(
                backOnClick = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = Main.EventDetails.routes){
            EventDetailScreen(
                backOnClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}

sealed class Main(val routes: String) {
    data object Home : Main("home")
    data object Menu : Main("menu")
    data object EntryPass : Main("entry_pass")
    data object Contact : Main("contact")
    data object AboutUs : Main("about_us")
    data object EventDetails : Main("event_details")
}