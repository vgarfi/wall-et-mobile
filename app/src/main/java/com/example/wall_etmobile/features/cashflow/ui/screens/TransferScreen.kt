package com.example.wall_etmobile.features.cashflow.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.RoundedImage
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowBaseScaffold
import com.example.wall_etmobile.ui.data.RoundedImageData
import kotlinx.coroutines.launch

@Composable
fun TransferScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit,
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var topPadding by remember { mutableIntStateOf(0) }
    val adaptivePadding : Dp = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT ->8.dp
        WindowWidthSizeClass.MEDIUM -> 16.dp
        WindowWidthSizeClass.EXPANDED -> 32.dp

        else -> { 1.dp}
    }
    val onclick : () -> Unit = {
            navigateToScreen("home", emptyMap())
    }
    val adaptiveSpacing : Dp = when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT ->{ 120.dp}
        WindowHeightSizeClass.MEDIUM ->{ 24.dp }
        WindowHeightSizeClass.EXPANDED ->{ 32.dp }
        else -> {1.dp}
    }

    when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT ->{topPadding= 40}
        WindowHeightSizeClass.MEDIUM ->{topPadding= 40}
        WindowHeightSizeClass.EXPANDED ->{topPadding= 160 }
        else -> {1.dp}
    }

    CashFlowBaseScaffold(bigText = stringResource(R.string.transfer), navController = navController, onArrowClick = onclick) {
        Column(
            modifier = Modifier.padding(top = (topPadding*0.4).dp)
        ) {
            TransferScreenContent(
                adaptivePadding = adaptivePadding,
                adaptiveSpacing = adaptiveSpacing,
                navigateToScreen = navigateToScreen
            )

        }
    }
}

@Composable
fun TransferScreenContent(
    adaptivePadding: Dp = 16.dp,
    adaptiveSpacing: Dp = 24.dp,
    navigateToScreen: (String, Map<String, String?>) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var adaptiveHeight by remember { mutableDoubleStateOf(200.0) }
    var adaptiveWidth by remember { mutableDoubleStateOf(400.0) }

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> adaptiveWidth = adaptiveWidth
        WindowWidthSizeClass.MEDIUM -> adaptiveWidth *= 1.5
        WindowWidthSizeClass.EXPANDED -> adaptiveWidth *= 2
    }
    when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> adaptiveHeight = adaptiveHeight
        WindowHeightSizeClass.MEDIUM -> adaptiveHeight *= 1.5
        WindowHeightSizeClass.EXPANDED -> adaptiveHeight *= 2
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(adaptivePadding),
        verticalArrangement = Arrangement.spacedBy(adaptiveSpacing)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TransferOptionCard(
                    iconRes = R.drawable.logo,
                    title = stringResource(R.string.to_user),
                    subtitle = stringResource(R.string.wallet),
                    onClick = {
                        navigateToScreen("transferTo", mapOf("target" to "user","page" to "0"))
                    }
                )

                TransferOptionCard(
                    iconRes = R.drawable.bank,
                    title = stringResource(R.string.to_account),
                    subtitle = stringResource(R.string.bankary),
                    onClick = {
                        navigateToScreen("transferTo", mapOf("target" to "account","page" to "0"))
                    }
                )
            }
        }

        item {
            Text(
                text = stringResource(R.string.favorites),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = adaptivePadding)
            )
        }
        items(getFrequentUsers()) { user ->
            FrequentUserRow(
                user = user,
                onClick = {
                    navigateToScreen("transferTo", mapOf("target" to "user","contactName" to user.name,"contactDetail" to user.contact,"page" to "1"))
                }
            )
        }

    }
}

fun getFrequentUsers(): List<User> {
    return listOf(
        User(name = "Tomas Borda", profilePic = R.drawable.tomas, contact = "tborda@gmail.com"),
        User(name = "Lautaro Paletta", profilePic = R.drawable.lautaro, contact = "lapaletta@gmail.com"),
        User(name = "Agustin Ronda", profilePic = R.drawable.agustin, contact = "aronda@gmail.com"),
        User(name = "Valentin Garfi", profilePic = R.drawable.valentin, contact = "vgarfi@gmail.com"),
    )
}
@Composable
fun FrequentUserRow(user: User, onClick: () -> Unit = {}) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val contactsSize = (screenHeight * 0.065).dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        RoundedImage(
            painter = painterResource(user.profilePic),
            size = contactsSize
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = user.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            color = MainBlack
        )
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "icono",
                    modifier = Modifier.size(13.dp),
                    tint = MainPurple
                )
                Box(modifier = Modifier.width(6.dp))
                Text(text = stringResource(R.string.wallet_account), color = Color.Gray)
            }
        }
    }
}

@Composable
fun TransferOptionCard(
    iconRes: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick : () -> Unit = {}
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var adaptiveHeight by remember { mutableDoubleStateOf(120.0) }
    var adaptiveWidth by remember { mutableDoubleStateOf(140.0) }
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> adaptiveWidth *= 1
        WindowWidthSizeClass.MEDIUM -> adaptiveWidth *= 1.5
        WindowWidthSizeClass.EXPANDED -> adaptiveWidth *= 2
    }
    when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> adaptiveHeight *= 1
        WindowHeightSizeClass.MEDIUM -> adaptiveHeight *= 1.1
        WindowHeightSizeClass.EXPANDED -> adaptiveHeight *= 1.2
    }

    adaptiveWidth = adaptiveWidth.coerceIn(150.0, 150.0)
    adaptiveHeight = adaptiveHeight.coerceIn(150.0, 150.0)

    Box(modifier = modifier
        .height(adaptiveHeight.dp)
        .width(adaptiveWidth.dp)
        .clickable(onClick = onClick),

    ) {
        Card(
            shape = RoundedCornerShape(12.dp),

            border = CardDefaults.outlinedCardBorder(),
            colors = CardDefaults.cardColors(
                contentColor = colorResource(R.color.black),
                containerColor = colorResource(R.color.purple_200).copy(alpha = 0.1f)
            ),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = MainPurple,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun TransferScreenPreviewCompact() {
    val navController = NavController(LocalContext.current)
    TransferScreen(
        navController = navController,navigateToScreen = { _, _ -> }
    )
}

@Preview(showBackground = true, widthDp = 720, heightDp = 1280)
@Composable
fun TransferScreenPreviewMedium() {
    val navController = NavController(LocalContext.current)
    TransferScreen(
        navController = navController,navigateToScreen = { _, _ -> }
    )
}

@Preview(showBackground = true, widthDp = 840, heightDp = 500)
@Composable
fun TransferScreenPreviewExpanded() {
    val navController = NavController(LocalContext.current)
    TransferScreen(
        navController = navController, navigateToScreen = { _, _ -> }
    )
}

data class User(
    val name: String,
    val profilePic: Int,
  //  val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val contact : String = ""
)