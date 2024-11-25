package com.example.wall_etmobile.core.navigation

import android.content.Intent
import android.net.Uri
import com.example.wall_etmobile.features.cashflow.ui.screens.TransferToScreen
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.navigateToScreen
import com.example.wall_etmobile.features.cards.ui.screens.CardsScreen
import com.example.wall_etmobile.features.home.ui.screens.HomeScreen
import com.example.wall_etmobile.features.profile.ui.screens.ProfileScreen
import com.example.wall_etmobile.features.transactions.ui.screens.TransactionsScreen
import com.example.wall_etmobile.features.auth.ui.screens.ForgotPasswordScreen
import com.example.wall_etmobile.features.auth.ui.screens.LoginScreen
import com.example.wall_etmobile.features.auth.ui.screens.RegisterScreen
import com.example.wall_etmobile.features.auth.ui.screens.RestorePasswordScreen
import com.example.wall_etmobile.features.auth.ui.screens.VerifyAccountScreen
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import com.example.wall_etmobile.features.cashflow.ui.screens.ChargeScreen
import com.example.wall_etmobile.features.cashflow.ui.screens.EnterFromScreen
import com.example.wall_etmobile.features.cashflow.ui.screens.EnterScreen
import com.example.wall_etmobile.features.cashflow.ui.composables.TransactionDetailsScreen
import com.example.wall_etmobile.features.cashflow.ui.screens.TransferScreen
import com.example.wall_etmobile.features.qr_scanner.ui.screens.QrScannerScreen
import com.example.wall_etmobile.features.splash.ui.SplashScreen
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel

@Composable
fun NavigationHostWrapper (
    navController: NavHostController,
    adaptiveInfo: WindowAdaptiveInfo,
    navigatorWrapper: NavigatorWrapper,
){
    val authViewModel: AuthViewModel = (LocalContext.current.applicationContext as MyApplication).authViewModel
    val transactionViewModel: TransactionViewModel = (LocalContext.current.applicationContext as MyApplication).transactionViewModel
    val cardsViewModel: CardViewModel = (LocalContext.current.applicationContext as MyApplication).cardsViewmodel
    val operationsViewModel: OperationsViewModel = (LocalContext.current.applicationContext as MyApplication).operationsViewModel

    NavHost(navController = navController,
        startDestination = Screen.SPLASH.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable(Screen.SCANQR.route) {
            val context = LocalContext.current
            QrScannerScreen(
                onQrCodeScanned = { result ->
                    if (result.startsWith("http://") || result.startsWith("https://")) {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse(result)
                        }
                        context.startActivity(intent)
                    } else {
                        println("Código QR no contiene una URL válida")
                    }
                    navController.popBackStack()
                    println("Resultado QR: $result")
                },
                onError = { error ->
                    println("Error: $error")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.HOME.route) {
            HomeScreen(navWrapper = navigatorWrapper, adaptiveInfo = adaptiveInfo, authViewModel = authViewModel, transactionViewModel = transactionViewModel, operationsViewModel = operationsViewModel
            , navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
            )
        }
        composable(Screen.TRANSACTIONS.route) {
            TransactionsScreen(navWrapper = navigatorWrapper, adaptiveInfo = adaptiveInfo, viewModel = transactionViewModel)
        }
        composable(Screen.CARDS.route) {
            CardsScreen(adaptiveInfo = adaptiveInfo)
        }
        composable(Screen.LOGIN.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(Screen.REGISTER.route) {
            RegisterScreen(navController, authViewModel)
        }
        composable(Screen.SPLASH.route) {
            SplashScreen(navController, authViewModel)
        }
        composable(Screen.FORGOTPASSWORD.route) {
            ForgotPasswordScreen(navController)
        }
        composable(Screen.VERIFYACCOUNT.route) {
            VerifyAccountScreen(navController, authViewModel)
        }
        composable(Screen.PROFILE.route) {
            ProfileScreen(navController, authViewModel)
        }
        composable(Screen.RESTOREPASSWORD.route) {
            RestorePasswordScreen(navController)
        }
        composable(Screen.TRANSFER.route) {
            TransferScreen(
                navController = navController,
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
            )
        }
        composable(Screen.CHARGE.route) {
            ChargeScreen(navController = navController, navigateToScreen = { route, args -> navigateToScreen(navController, route, args) }, operationsViewModel = operationsViewModel,)
        }
        composable(Screen.ENTER.route) {
            EnterScreen(
                navController = navController,
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
            )
        }
        composable(
            Screen.ENTERFROM.route,
            arguments = listOf(navArgument("source") { type = NavType.StringType; nullable = true },
                navArgument("page") { type = NavType.StringType; nullable = true })
        ) {
                backStackEntry ->
            val source = backStackEntry.arguments?.getString("source")
            val page = backStackEntry.arguments?.getString("page")

            EnterFromScreen(
                source = source,
                navController = navController,
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
                page = page?.toInt(),
                operationsViewModel = operationsViewModel,
                userViewModel = authViewModel,
                transactionViewModel = transactionViewModel,
            )
        }
        composable(Screen.TRANSACTIONDETAILS.route) {
            TransactionDetailsScreen(
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
                operationsViewModel = operationsViewModel,
                )
        }
        composable(
            Screen.TRANSFERTO.route,
            arguments = listOf(navArgument("target") { type = NavType.StringType; nullable = true },
                navArgument("page") { type = NavType.StringType; nullable = true })
        ) {
                backStackEntry ->
            val target = backStackEntry.arguments?.getString("target")
            val page = backStackEntry.arguments?.getString("page")
            val contactName = backStackEntry.arguments?.getString("contactName")
            val contactDetail = backStackEntry.arguments?.getString("contactDetail")

            TransferToScreen(
                target = target,
                navController = navController,
                navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
                page = page?.toInt(),
                contactName = contactName,
                contactDetail = contactDetail,
                userViewModel = authViewModel,
                cardViewModel = cardsViewModel,
                transactionViewModel = transactionViewModel,
                operationsViewModel = operationsViewModel,
            )
        }
    }
}