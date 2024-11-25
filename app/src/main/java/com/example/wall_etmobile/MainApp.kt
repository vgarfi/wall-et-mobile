package com.example.wall_etmobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.core.navigation.NavigationHostWrapper
import com.example.wall_etmobile.core.designKit.BottomAppBarButton
import com.example.wall_etmobile.core.navigation.NavigatorWrapper
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.core.theme.WalletMobileTheme
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.core.theme.MainBlack2
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.LongArrowAltLeft
import compose.icons.fontawesomeicons.solid.Qrcode

fun onButtonClick(screen: Screen, currentDestination: String? = null, navigatorWrapper: NavigatorWrapper){
    if (currentDestination != screen.route) {
        navigatorWrapper.navigate(screen)
    }
}

fun navigateToScreen(navController: NavController, route: String, args: Map<String, String?>) {
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
    val nonBottomBarRoutes = listOf(
        Screen.LOGIN.route,
        Screen.REGISTER.route,
        Screen.FORGOTPASSWORD.route,
        Screen.VERIFYACCOUNT.route,
        Screen.RESTOREPASSWORD.route,
        Screen.TRANSACTIONDETAILS.route,
        Screen.TRANSFERTO.route,
        Screen.TRANSFER.route,
        Screen.ENTER.route,
        Screen.ENTERFROM.route,
        Screen.CHARGE.route,
        Screen.SCANQR.route,
        Screen.SPLASH.route,
    )

    val topBarRoutes = listOf(
        Screen.LOGIN.route,
        Screen.REGISTER.route,
        Screen.FORGOTPASSWORD.route,
        Screen.VERIFYACCOUNT.route,
        Screen.RESTOREPASSWORD.route,
    )

    val screens = listOf(
        Screen.HOME,
        Screen.TRANSACTIONS,
        Screen.CARDS,
        Screen.PROFILE
    )

    WalletMobileTheme {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route
        val navigatorWrapper = NavigatorWrapper(navController)

        with(adaptiveInfo) {
            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM || windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED) {
                NavigationSuiteType.NavigationRail
            } else {
                NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
            }
        }
        val currentRoute = navBackStackEntry?.destination?.route
        val currentRouteModel = Screen.allScreens.find { it.route == currentRoute }
        if(nonBottomBarRoutes.contains(currentDestination)) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    if (topBarRoutes.contains(currentDestination)) Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MainWhite)
                            .statusBarsPadding()
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            if(currentRouteModel?.route != Screen.LOGIN.route) {
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
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = if(currentRouteModel?.label != null) stringResource(currentRouteModel.label) else "",
                                color = MainBlack2,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                            if(currentRouteModel?.route != Screen.LOGIN.route) {
                                Spacer(modifier = Modifier.size(30.dp))
                            }
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                },
            ) { innerPadding -> Box(
                modifier = Modifier.fillMaxSize().padding(if (currentRouteModel?.route == Screen.LOGIN.route || currentRouteModel?.route == Screen.RESTOREPASSWORD.route) innerPadding else PaddingValues(0.dp))
                ) {
                NavigationHostWrapper(navController = navController, adaptiveInfo = adaptiveInfo, navigatorWrapper = navigatorWrapper)
            }

            }
        }
        else {
            NavigationSuiteScaffold(
                navigationSuiteItems = {
                    Screen.entries.subList(0, 2).forEach {
                        item(
                            icon = {
                                BottomAppBarButton(
                                    icon = it.icon,
                                    label = stringResource(it.label),
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
                                    navController.navigate(Screen.SCANQR.route)
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
                                    label = stringResource(it.label),
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
                NavigationHostWrapper(navController = navController, adaptiveInfo = adaptiveInfo, navigatorWrapper = navigatorWrapper )
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