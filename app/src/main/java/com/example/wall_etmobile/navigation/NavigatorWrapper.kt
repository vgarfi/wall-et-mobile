package com.example.wall_etmobile.navigation

import androidx.navigation.NavController

class NavigatorWrapper(
    private val navController: NavController
) {
    fun navigate(screen: Screen){
        navController.navigate(screen.route) {
            launchSingleTop = screen.launchSingleTop
            restoreState = screen.restoreState
            popUpTo(navController.graph.startDestinationId) {
                saveState = screen.saveState
            }
        }
    }
    fun navigateTransfer(){
        navController.navigate(Screen.TRANSFER.route) {
            popUpTo(Screen.HOME.route) { inclusive = false }
            launchSingleTop = true
        }
    }

    fun navigateCharge(){
        navController.navigate(Screen.CHARGE.route) {
            popUpTo(Screen.HOME.route) { inclusive = false }
            launchSingleTop = true
        }
    }

    fun navigateIncome(){
        navController.navigate(Screen.INCOME.route) {
            popUpTo(Screen.HOME.route) { inclusive = false }
            launchSingleTop = true
        }
    }

    fun navigateToDetailsFromMovements(){
        navController.navigate(Screen.TRANSACTIONDETAILS.route) {
            popUpTo(Screen.TRANSACTIONS.route) { inclusive = false }
            launchSingleTop = true
        }
    }

    fun navigateToDetailsFromHome(){
        navController.navigate(Screen.TRANSACTIONDETAILS.route) {
            popUpTo(Screen.HOME.route) { inclusive = false }
            launchSingleTop = true
        }
    }
}


