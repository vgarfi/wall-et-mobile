package com.example.wall_etmobile

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.design_kit.shared.BottomAppBarButton
import com.example.wall_etmobile.navigation.NavigatorWrapper
import com.example.wall_etmobile.screens.CardsScreen
import com.example.wall_etmobile.screens.HomeScreen
import com.example.wall_etmobile.screens.ProfileScreen
import com.example.wall_etmobile.screens.TransactionsScreen
import com.example.wall_etmobile.screens.cashflow.ChargeScreen
import com.example.wall_etmobile.screens.cashflow.EnterScreen
import com.example.wall_etmobile.screens.cashflow.TransactionDetailsScreen
import com.example.wall_etmobile.screens.cashflow.TransferScreen
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainWhite
import com.example.wall_etmobile.ui.theme.WalletMobileTheme
import com.example.wall_etmobile.navigation.Screen
import com.example.wall_etmobile.screens.cashflow.TransferAmountScreen
import com.example.wall_etmobile.screens.cashflow.TransferToScreen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Qrcode

fun onButtonClick(screen: Screen, currentDestination: String? = null, navigatorWrapper: NavigatorWrapper){
    if (currentDestination != screen.route) {
        navigatorWrapper.navigate(screen)
    }
}

fun navigateToScreen(navController: NavController, route: String, args: Map<String, String?>) {
    // Construct the route dynamically based on the arguments
    val routeWithArgs = buildString {
        append(route)
        if (args.isNotEmpty()) {
            append("?")
            append(args.entries.joinToString("&") { "${it.key}=${it.value}" })
        }
    }
    navController.navigate(routeWithArgs)
}
@Composable
fun MainApp() {
    WalletMobileTheme {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route
        val navigatorWrapper = NavigatorWrapper(navController)

        with(adaptiveInfo) {
            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM) {
                NavigationSuiteType.NavigationRail
            } else {
                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
            }
        }

        NavigationSuiteScaffold(
            navigationSuiteItems = {
                Screen.entries.subList(0, 2).forEach {
                    item(
                        icon = {
                            BottomAppBarButton(
                                icon = it.icon,
                                label = it.label,
                                isSelected = currentDestination == it.route,
                                isTiny = it.tiny,
                                onClick = {
                                    onButtonClick(currentDestination = currentDestination, screen = it, navigatorWrapper = navigatorWrapper)
                                }
                            )
                        },
                        selected = false,
                        onClick = {
                            onButtonClick(currentDestination = currentDestination, screen = it, navigatorWrapper = navigatorWrapper)
                        }
                    )
                }
                item(
                    icon = {
                        FloatingActionButton(
                            containerColor = MainPurple,
                            onClick = {

                            },
                            contentColor = MainWhite,
                            shape = CircleShape,
                            modifier = Modifier
                                .size(65.dp)
                        ) {
                            Icon(
                                FontAwesomeIcons.Solid.Qrcode,
                                contentDescription = "QR",
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    },
                    selected = false,
                    onClick = {}
                )
                Screen.entries.subList(2, 4).forEach {
                    item(
                        icon = {
                            BottomAppBarButton(
                                icon = it.icon,
                                label = it.label,
                                isSelected = currentDestination == it.route,
                                isTiny = it.tiny,
                                onClick = {
                                    onButtonClick(currentDestination = currentDestination, screen = it, navigatorWrapper = navigatorWrapper)
                                }
                            )
                        },
                        selected = false,
                        onClick = {
                            onButtonClick(currentDestination = currentDestination, screen = it, navigatorWrapper = navigatorWrapper)
                        }
                    )
                }

            },
            navigationSuiteColors = NavigationSuiteDefaults.colors(MainWhite),
            layoutType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        ) {
            NavHost(navController = navController,
                startDestination = Screen.HOME.route,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
            ) {
                composable(Screen.HOME.route) {
                    HomeScreen(navWrapper = navigatorWrapper, adaptiveInfo = adaptiveInfo)
                }
                composable(Screen.TRANSACTIONS.route) {
                    TransactionsScreen(navWrapper = navigatorWrapper, adaptiveInfo = adaptiveInfo)
                }
                composable(Screen.CARDS.route) {
                    CardsScreen()
                }
                composable(Screen.PROFILE.route) {
                    ProfileScreen()
                }
                composable(Screen.TRANSFER.route) {
                    TransferScreen(
                        navController = navController,
                        navigateToScreen = { route, args -> navigateToScreen(navController, route, args) }
                    )
                }
                composable(Screen.CHARGE.route) {
                    ChargeScreen(navController = navController)
                }
                composable(Screen.INCOME.route) {
                    EnterScreen(navController = navController)
                }
                composable(Screen.TRANSACTIONDETAILS.route) {
                    TransactionDetailsScreen(navController = navController)
                }
                composable(
                    Screen.TRANSFERTO.route,
                    arguments = listOf(navArgument("target") { type = NavType.StringType; nullable = true },
                        navArgument("page") { type = NavType.StringType; nullable = true })
                ) {
                    backStackEntry ->
                    val target = backStackEntry.arguments?.getString("target")
                    val page = backStackEntry.arguments?.getString("page")
                    val to = backStackEntry.arguments?.getString("to")
                    TransferToScreen(
                        target = target,
                        navController = navController,
                        navigateToScreen = { route, args -> navigateToScreen(navController, route, args) },
                        page = page?.toInt(),
                        to = to
                        )
                }

            }
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp")
@Preview(device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun MainAppTestPreview() {
    MainApp()
}
