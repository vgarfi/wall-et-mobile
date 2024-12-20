package com.example.wall_etmobile

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wall_etmobile.features.auth.datasource.UserRemoteDataSource
import com.example.wall_etmobile.core.config.RetrofitClient
import com.example.wall_etmobile.core.config.SessionManager
import com.example.wall_etmobile.features.auth.repository.UserRepository
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import com.example.wall_etmobile.features.cards.datasource.CardRemoteDataSource
import com.example.wall_etmobile.features.cards.repository.CardRepository
import com.example.wall_etmobile.features.transactions.datasource.TransactionRemoteDataSource
import com.example.wall_etmobile.features.transactions.repository.TransactionRepository
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import com.example.wall_etmobile.features.cashflow.repository.OperationsRepository
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel


class MyApplication : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(
            sessionManager,
            RetrofitClient.getUserApiService(this),
            RetrofitClient.getUserWalletApiService(this),
        )

    private val cardRemoteDataSource: CardRemoteDataSource
        get() = CardRemoteDataSource(RetrofitClient.getCardApiService(this))

    private val transactionRemoteDataSource: TransactionRemoteDataSource
        get() = TransactionRemoteDataSource(RetrofitClient.getTransactionApiService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val cardRepository: CardRepository
        get() = CardRepository(cardRemoteDataSource)

    val transactionRepository: TransactionRepository
        get() = TransactionRepository(transactionRemoteDataSource)

    val operationsRepository: OperationsRepository
        get() = OperationsRepository()

    val transactionViewModel: TransactionViewModel
        @Composable
        get() = viewModel(factory = TransactionViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))

    val cardsViewmodel: CardViewModel
        @Composable
        get() = viewModel(factory = CardViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))

    val authViewModel: AuthViewModel
        @Composable
        get() = viewModel(factory = AuthViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))

    val operationsViewModel: OperationsViewModel
        @Composable
        get() = viewModel(factory = OperationsViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication))
}