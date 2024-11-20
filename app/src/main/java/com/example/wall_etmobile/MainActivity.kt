package com.example.wall_etmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
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
import com.example.wall_etmobile.screens.auth.ForgotPasswordScreen
import com.example.wall_etmobile.screens.auth.LoginScreen
import com.example.wall_etmobile.screens.auth.RegisterScreen
import com.example.wall_etmobile.screens.auth.RestorePasswordScreen
import com.example.wall_etmobile.screens.auth.VerifyAccountScreen
import com.example.wall_etmobile.screens.auth.WelcomeScreen
import com.example.wall_etmobile.ui.theme.MainBlack
import com.example.wall_etmobile.ui.theme.MainBlack2
import com.example.wall_etmobile.ui.theme.MainGrey
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainWhite
import com.example.wall_etmobile.ui.theme.WalletMobileTheme
import com.pathak.barberapp.navigation.Screen
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowCircleLeft
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Backward
import compose.icons.fontawesomeicons.solid.Bars
import compose.icons.fontawesomeicons.solid.Bell
import compose.icons.fontawesomeicons.solid.LongArrowAltLeft
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
    val currentRouteModel = Screen.allScreens.find { it.route == currentRoute }

    val nonBottomBarRoutes = listOf(
        Screen.Welcome.route,
        Screen.Login.route,
        Screen.Register.route,
        Screen.ForgotPassword.route,
        Screen.VerifyAccount.route,
        Screen.RestorePassword.route,
    )

    val topBarRoutes = listOf(
        Screen.Login.route,
        Screen.Register.route,
        Screen.ForgotPassword.route,
        Screen.VerifyAccount.route,
        Screen.RestorePassword.route,
    )

    val screens = listOf(
        Screen.Home,
        Screen.Transactions,
        Screen.Cards,
        Screen.Profile
    )

    return Scaffold(
    contentWindowInsets = WindowInsets.safeDrawing,
    modifier = Modifier.fillMaxSize(),
    topBar = {
        if(topBarRoutes.contains(currentRoute)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MainWhite)
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.LongArrowAltLeft,
                            contentDescription = "Back",
                            tint = MainPurple,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = currentRouteModel?.label ?: "",
                        color = MainBlack2,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    },
    floatingActionButton = {
        if(!nonBottomBarRoutes.contains(currentRoute)) {
            Box() {
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
        if(!nonBottomBarRoutes.contains(currentRoute)) {
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
    ) { innerPadding ->
        NavHost(navController = navController,
            startDestination = Screen.Welcome.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Transactions.route) {
                TransactionsScreen()
            }
            composable(Screen.Cards.route) {
                CardsScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen(navController)
            }
            composable(Screen.Welcome.route) {
                WelcomeScreen(navController)
            }
            composable(Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(Screen.Register.route) {
                RegisterScreen(navController)
            }
            composable(Screen.ForgotPassword.route) {
                ForgotPasswordScreen(navController)
            }
            composable(Screen.VerifyAccount.route) {
                VerifyAccountScreen(navController)
            }
            composable(Screen.RestorePassword.route) {
                RestorePasswordScreen(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    MainApp()
}