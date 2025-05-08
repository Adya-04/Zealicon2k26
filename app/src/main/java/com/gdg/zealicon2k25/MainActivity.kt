package com.gdg.zealicon2k25

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.gdg.zealicon2k25.data.models.PaymentVerificationRequest
import com.gdg.zealicon2k25.presentation.navigation.NavRoutes
import com.gdg.zealicon2k25.presentation.navigation.RootNavGraph
import com.gdg.zealicon2k25.presentation.ui.EntryPass
import com.gdg.zealicon2k25.presentation.ui.EventDetailScreen
import com.gdg.zealicon2k25.presentation.ui.HomeScreen
import com.gdg.zealicon2k25.presentation.ui.MenuScreen
import com.gdg.zealicon2k25.presentation.ui.PhotoUploadScreen
import com.gdg.zealicon2k25.presentation.ui.PurchaseZealScreen
import com.gdg.zealicon2k25.presentation.ui.components.PaymentSuccessBottomSheet
import com.gdg.zealicon2k25.presentation.ui.theme.Zealicon2K25Theme
import com.gdg.zealicon2k25.presentation.ui.viewmodels.ImageUploadViewModel
import com.gdg.zealicon2k25.presentation.ui.viewmodels.PaymentViewModel
import com.gdg.zealicon2k25.utils.PaymentState
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import com.gdg.zealicon2k25.presentation.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), PaymentResultWithDataListener {
    private val viewModel: PaymentViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val authViewModel: AuthViewModel= hiltViewModel()
//            val initToken = authViewModel.initToken.collectAsState(initial = "")
            Zealicon2K25Theme {
               RootNavGraph(rememberNavController(), activity = this, paymentViewModel = viewModel)
//                val startDestination = if (initToken.value.isNotEmpty()) {
//                    NavRoutes.Main.route
//                } else {
//                    NavRoutes.Onboarding.route
//                }
//                RootNavGraph(navController = rememberNavController() , startDestination=startDestination )
            }
//            PurchaseZealScreen(
//                activity = this,
//                paymentViewModel = viewModel,
//            )
        }
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Log.d("RAZORPAY", p1.toString())
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Verifying Payment...", Toast.LENGTH_SHORT).show()
        viewModel.paymentVerify(
            PaymentVerificationRequest(
                razorpay_payment_id = p1?.paymentId ?: "",
                razorpay_order_id = p1?.orderId ?: "",
                razorpay_signature = p1?.signature ?: ""
            )
        )
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Log.e("Razorpay", "Payment failed/cancelled: Code $p0, Response: $p1")

        when (p0) {
            else -> {
                viewModel.updateBottomSheetState(state = PaymentState.Error)
            }
        }

    }


}

fun Activity.lockOrientationPortrait() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Zealicon2K25Theme {
        Greeting("Android")
    }
}