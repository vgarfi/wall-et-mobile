package com.example.wall_etmobile.features.home.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainWhite
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.core.data.MovementData
import com.example.wall_etmobile.core.designKit.CircularAddButton
import com.example.wall_etmobile.ui.data.RoundedImageData
import com.example.wall_etmobile.features.home.ui.composables.HomeHeader
import com.example.wall_etmobile.features.home.ui.composables.MountVisor
import com.example.wall_etmobile.features.home.ui.composables.SectionButtons
import com.example.wall_etmobile.core.designKit.MovementTileList
import com.example.wall_etmobile.core.designKit.RoundedImageWithText
import com.example.wall_etmobile.core.designKit.TitleWithTextButton
import com.example.wall_etmobile.core.designKit.TransactionTypeStyle
import com.example.wall_etmobile.core.navigation.NavigatorWrapper
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainGrey
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import com.example.wall_etmobile.features.cards.viewmodel.CardViewModel
import com.example.wall_etmobile.features.cashflow.viewmodel.OperationsViewModel
import com.example.wall_etmobile.features.home.ui.HomeViewModel
import com.example.wall_etmobile.features.home.ui.composables.CvuBottomSheet
import com.example.wall_etmobile.features.transactions.ui.TransactionViewModel

@Composable
fun HomeScreen(
    navWrapper: NavigatorWrapper,
    adaptiveInfo: WindowAdaptiveInfo,
    cardsViewModel: CardViewModel = ( LocalContext.current.applicationContext as MyApplication).cardsViewmodel,
    transactionViewModel: TransactionViewModel,
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(LocalContext.current.applicationContext as MyApplication)),
    authViewModel: AuthViewModel,
    operationsViewModel : OperationsViewModel,
) {
    val currentUser by remember { mutableStateOf(authViewModel.getUserData()) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val isRotated = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE  //si es verdadero esta rotada

    val uiHomeState = homeViewModel.uiState

    val uiTransactionState = transactionViewModel.uiState

    LaunchedEffect(Unit) {
        cardsViewModel.getCards()
        transactionViewModel.getTransactions()
        authViewModel.updateBalance()
    }

    val cvu = currentUser?.wallet?.cbu ?: "--------"
    val username = currentUser?.firstName ?: "usuario"

    var movementTileHeight by remember { mutableStateOf((screenHeight * 0.065).dp) }
    var movementTitleSize by remember { mutableStateOf((screenHeight * 0.0225).sp) }
    var movementSubTitleSize by remember { mutableStateOf((screenHeight * 0.015).sp) }
    var movementMountSize by remember { mutableStateOf((screenHeight * 0.02).sp) }
    val onMovementTileClick = { id: Int ->

    }

    val contactsSize = (screenHeight * 0.065).dp

    val contacts = listOf(
        RoundedImageData(painter = painterResource(R.drawable.tomas), title = "Tomas", size = contactsSize),
        RoundedImageData(painter = painterResource(R.drawable.agustin), title = "Agustín", size = contactsSize),
        RoundedImageData(painter = painterResource(R.drawable.lautaro), title = "Lautaro", size = contactsSize),
        RoundedImageData(painter = painterResource(R.drawable.valentin), title = "Valentín", size = contactsSize),
        )


    val transactionsStyle: List<MovementData> = transactionViewModel.uiState.completedTransactions?.map {
        val type: TransactionTypeStyle
        val subTitle: String
        when (it.type.description) {
            TransactionTypeStyle.CHARGE.description -> {
                type = TransactionTypeStyle.CHARGE
                subTitle = it.updatedAt.toString() + " " + stringResource(R.string.from) + " " + it.payer?.firstName + ", " + it.payer?.lastName
            }
            TransactionTypeStyle.TRANSFER.description -> {
                type = TransactionTypeStyle.TRANSFER
                subTitle = it.updatedAt.toString() + " " + stringResource(R.string.to) + " " + it.payer?.firstName + ", " + it.payer?.lastName
            }
            else -> {
                type = TransactionTypeStyle.INCOME
                subTitle = it.updatedAt.toString() + " " + stringResource(R.string.to) + " " + it.payer?.firstName + ", " + it.payer?.lastName
            }
        }

        MovementData(
            tileHeight = movementTileHeight,
            title = stringResource(it.type.description),
            titleSize = movementTitleSize,
            subTitle = subTitle,
            subTitleSize = movementSubTitleSize,
            mount = it.amount,
            mountSize = movementMountSize,
            transactionTypeStyle = type,
            onClick = onMovementTileClick,
            id = it.id
        )
    } ?: emptyList()


    if (isRotated || adaptiveInfo.windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT) {
        movementTileHeight = (screenHeight*0.15).dp
        movementTitleSize = (screenHeight*0.05).sp
        movementSubTitleSize = (screenHeight*0.04).sp
        movementMountSize = (screenHeight*0.04).sp

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MainWhite)
        ) {
            Column{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((screenHeight * 0.50).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home_header),
                        contentDescription = "Fondo",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        HomeHeader(onClick = { homeViewModel.toggleShowCvu() })
                        MountVisor(
                            mount = authViewModel.getUserData()?.wallet?.balance ?: -1.0,
                            onClick = { homeViewModel.toggleShowMoney() },
                            showMoney = uiHomeState.showMoney
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .width((screenWidth * 0.5).dp)
                            .padding(8.dp)
                    ) {
                        TitleWithTextButton(
                            title = R.string.last_transactions,
                            textButton = R.string.see_moore,
                            onClick = { navWrapper.navigate(Screen.TRANSACTIONS) }
                        )

                        if (uiTransactionState.completedTransactions != null) {
                            MovementTileList(movements = transactionsStyle)
                        } else {
                            transactionViewModel.getTransactions()
                        }

                    }
                    Box(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        SectionButtons(navWrapper = navWrapper ,operationsViewModel)
                    }
                }
            }
        }
    }
    else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MainWhite)
        ) {
            Image(
                painter = painterResource(id = R.drawable.home_header),
                contentDescription = "Fondo",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 40.dp)
            ) {
                Box(modifier = Modifier.height((screenHeight*0.005).dp))
                HomeHeader(onClick = { homeViewModel.toggleShowCvu() })
                Box(modifier = Modifier.height((screenHeight*0.035).dp))
                MountVisor(
                    mount = authViewModel.getUserData()?.wallet?.balance ?: -1.0,
                    onClick = { homeViewModel.toggleShowMoney() },
                    showMoney = uiHomeState.showMoney
                )
                Box(modifier = Modifier.height((screenHeight*0.025).dp))
                SectionButtons(navWrapper = navWrapper ,operationsViewModel)
                Box(modifier = Modifier.height((screenHeight*0.035).dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.favorites),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        color = MainBlack,
                    )
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        CircularAddButton(
                            size = contactsSize,
                            text = stringResource(R.string.add)
                        )
                        Spacer(modifier = Modifier.padding(end = 25.dp))
                        LazyRow {
                            items(items = contacts) {
                                RoundedImageWithText(size = it.size, title = it.title, painter = it.painter)
                                Spacer(modifier = Modifier.padding(end = 25.dp))
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 2.dp))
                    }

                }
                Box(modifier = Modifier.height((screenHeight*0.02).dp))
                Column(
                    modifier = Modifier
                        .height((screenHeight * 0.6).dp)
                        .fillMaxWidth()
                ) {
                    TitleWithTextButton(
                        title = R.string.last_transactions,
                        textButton = R.string.see_moore,
                        onClick = { navWrapper.navigate(Screen.TRANSACTIONS) }
                    )
                    if (uiTransactionState.completedTransactions != null) {
                        MovementTileList(movements = transactionsStyle)
                    } else {
                        transactionViewModel.getTransactions()
                    }
                }
            }
        }
    }
    if (uiHomeState.showCvu) {
        CvuBottomSheet(
                onDismissRequest = { homeViewModel.toggleShowCvu() },
                cvu = cvu,
                username = username
            )
    }
}


