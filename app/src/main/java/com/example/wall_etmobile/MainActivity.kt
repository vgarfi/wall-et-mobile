package com.example.wall_etmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wall_etmobile.design_kit.shared.BottomAppBarButton
import com.example.wall_etmobile.screens.CardsScreen
import com.example.wall_etmobile.screens.HomeScreen
import com.example.wall_etmobile.screens.ProfileScreen
import com.example.wall_etmobile.screens.TransactionsScreen
import com.example.wall_etmobile.screens.cashflow.ChargeScreen
import com.example.wall_etmobile.screens.cashflow.EnterScreen
import com.example.wall_etmobile.screens.cashflow.TransferScreen
import com.example.wall_etmobile.ui.theme.MainGrey
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainWhite
import com.example.wall_etmobile.ui.theme.WalletMobileTheme
import com.pathak.barberapp.navigation.Screen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Qrcode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        window.statusBarColor = MainPurple.toArgb()
        setContent {
            WalletMobileTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val hideBottomNavRoutes = listOf(Screen.Transfer.route, Screen.Charge.route, Screen.Enter.route)

    val screens = listOf(
        Screen.Home,
        Screen.Transactions,
        Screen.Cards,
        Screen.Profile
    )

    return Scaffold(
    contentWindowInsets = WindowInsets.safeDrawing,
    modifier = Modifier.fillMaxWidth(),
        floatingActionButton = {
            if (currentRoute !in hideBottomNavRoutes) {
            Box(){
                FloatingActionButton(
                    containerColor = MainPurple,
                    onClick = {

                    },
                    contentColor = MainWhite,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(65.dp)
                        .offset(y = 60.dp)
                ) {
                    Icon(
                        FontAwesomeIcons.Solid.Qrcode,
                        contentDescription = "QR",
                        modifier = Modifier.size(35.dp)
                    )
                }
            }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            if (currentRoute !in hideBottomNavRoutes) {

            BottomAppBar(
                containerColor = MainWhite,
                contentColor = MainGrey,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                ) {

                    screens.subList(0,2).forEach { screen ->
                        BottomAppBarButton(icon = screen.icon, label = screen.label, isSelected = currentRoute == screen.route, isTiny = screen.tiny, onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            }
                        },)
                    }
                    Box(modifier = Modifier.width(60.dp))
                    screens.subList(2,4).forEach { screen ->
                        BottomAppBarButton(icon = screen.icon, label = screen.label, isSelected = currentRoute == screen.route, isTiny = screen.tiny, onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
                            }
                        },)
                    }
                }
            }
            }
        },
    ) { _ ->
        NavHost(navController = navController,
            startDestination = Screen.Home.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.Transactions.route) {
                TransactionsScreen()
            }
            composable(Screen.Cards.route) {
                CardsScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Transfer.route) {
                TransferScreen(navController = navController)
            }
            composable(Screen.Charge.route) {
                ChargeScreen(navController = navController)
            }
            composable(Screen.Enter.route) {
                EnterScreen(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    MainApp()
}