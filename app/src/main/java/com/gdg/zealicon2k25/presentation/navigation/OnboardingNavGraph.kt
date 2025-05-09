package com.gdg.zealicon2k25.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.gdg.zealicon2k25.presentation.ui.LoginScreen
import com.gdg.zealicon2k25.presentation.ui.PhotoUploadScreen
import com.gdg.zealicon2k25.presentation.ui.RegisterScreen
import com.gdg.zealicon2k25.presentation.ui.VerifyOTPScreen
import com.gdg.zealicon2k25.presentation.ui.WelcomeScreen
import com.gdg.zealicon2k25.presentation.ui.viewmodels.ImageUploadViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel

fun NavGraphBuilder.onboardingNavGraph(navHostController: NavHostController ,
                                       authViewModel: AuthViewModel,
                                       imageViewModel: ImageUploadViewModel,
                                       paymentViewModel: PaymentViewModel
) {
    navigation(
        route = NavRoutes.Onboarding.route,
        startDestination = Auth.WelcomeScreen.route
    ) {
        composable(route = Auth.WelcomeScreen.route) {
            WelcomeScreen(
                loginOnClick = { navHostController.navigate(Auth.Login.route) },
                registerOnClick = { navHostController.navigate(Auth.Register.route) }
            )
        }

        composable(route = Auth.Login.route) {
            LoginScreen(
                loginOnClick = {
                    navHostController.navigate(Auth.VerifyOTP.route)
                },
                authViewModel = authViewModel
            )
        }

        composable(route = Auth.Register.route) {
            RegisterScreen(
                registerOnClick = {
                    navHostController.navigate(Auth.VerifyOTP.route)
                },
                authViewModel = authViewModel
            )
        }

        composable(route = Auth.VerifyOTP.route) {
            VerifyOTPScreen(
                verifyToPhoto = {
                    navHostController.navigate(Auth.PhotoUpload.route)
                },
                verifyToHome = {
                    navHostController.navigate(NavRoutes.Main.route){
                        popUpTo(NavRoutes.Onboarding.route){
                            inclusive = true
                        }
                    }
                },
                authViewModel = authViewModel,
                paymentViewModel = paymentViewModel
            )
        }

        composable(route = Auth.PhotoUpload.route){
            PhotoUploadScreen(
                authViewModel = authViewModel,
                imageViewModel = imageViewModel,
                registerOnClick = {
                    navHostController.navigate(NavRoutes.Payment.route){
                        popUpTo(NavRoutes.Onboarding.route){
                            inclusive = true
                        }
                    }
                },
            )
        }

    }
}

sealed class Auth(val route: String) {
    data object WelcomeScreen : Auth("welcome_screen")
    data object Login : Auth("login")
    data object Register : Auth("register")
    data object VerifyOTP : Auth("verify_otp")
    data object PhotoUpload : Auth("photo_upload")
}