package com.gdg.zealicon2k25.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.gdg.zealicon2k25.presentation.ui.MerchListScreen
import com.gdg.zealicon2k25.presentation.ui.MerchScreen
import com.gdg.zealicon2k25.presentation.ui.TeamScreen
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.EventsViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.MerchViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.mainNavGraph(
    navHostController: NavHostController,
    eventsViewModel: EventsViewModel,
    authViewModel: AuthViewModel,
    paymentViewModel: PaymentViewModel,
    merchViewModel: MerchViewModel
) {
    navigation(
        startDestination = Main.Home.routes,
        route = NavRoutes.Main.route
    ) {
        composable(route = Main.Home.routes) {
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
                paymentViewModel = paymentViewModel,
                merchListing = {
                    navHostController.navigate(route = Main.Merch.routes)
                },
                eventsViewModel = eventsViewModel,
                authViewModel = authViewModel
            )
        }

        composable(route = Main.Menu.routes) {
            MenuScreen(
                aboutUsClick = {
                    navHostController.navigate(route = Main.AboutUs.routes)
                },
                contactUsClick = {
                    navHostController.navigate(route = Main.Contact.routes)
                },
                backOnClick = {
                    navHostController.popBackStack()
                },
                teamClick = {
                    navHostController.navigate(route = Main.Team.routes)
                }
            )
        }

        composable(route = Main.EntryPass.routes) {
            EntryPass(
                backOnClick = {
                    navHostController.popBackStack()
                },
                paymentViewModel = paymentViewModel
            )
        }

        composable(route = Main.Contact.routes) {
            ContactUsScreen(
                backOnClick = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = Main.AboutUs.routes) {
            AboutUsScreen(
                backOnClick = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = Main.Team.routes) {
            TeamScreen()
        }

        composable(route = Main.Merch.routes) {
            MerchListScreen(
                authViewModel=authViewModel ,
                merchViewModel = merchViewModel,
                merchDetails = {
                    navHostController.navigate(route=Main.MerchDetail.routes)
                }
            )
        }

        composable(route=Main.MerchDetail.routes) {
            MerchScreen(
                merchViewModel = merchViewModel
            )
        }

        composable(route = Main.EventDetails.routes) {
            EventDetailScreen(
                backOnClick = {
                    navHostController.popBackStack()
                },
                eventsViewModel = eventsViewModel
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
    data object Team : Main("team")
    data object Merch : Main("merch")
    data object MerchDetail : Main("merch_detail")
}