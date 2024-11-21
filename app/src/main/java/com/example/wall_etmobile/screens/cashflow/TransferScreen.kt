package com.example.wall_etmobile.screens.cashflow

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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


@Composable
fun TransferScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit
) {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    var topPadding by remember { mutableIntStateOf(0) }
    val adaptivePadding : Dp = when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT ->8.dp
        WindowWidthSizeClass.MEDIUM -> 16.dp
        WindowWidthSizeClass.EXPANDED -> 32.dp

        else -> { 0.dp}
    }
    val adaptiveSpacing : Dp = when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT ->{ 120.dp}
        WindowHeightSizeClass.MEDIUM ->{ 24.dp }
        WindowHeightSizeClass.EXPANDED ->{ 32.dp }
        else -> {0.dp}
    }

    when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT ->{topPadding= 40}
        WindowHeightSizeClass.MEDIUM ->{topPadding= 40}
        WindowHeightSizeClass.EXPANDED ->{topPadding= 160 }
        else -> {0.dp}
    }


    CashFlowBaseScaffold(bigText = "Transferir", navController = navController) {
        Column(
            modifier = Modifier

                .padding(top = topPadding.dp)
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

    // Adjusting adaptive height/width based on window size
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
        verticalArrangement = Arrangement.spacedBy(adaptiveSpacing) // Adds spacing between items
    ) {

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TransferOptionCard(
                    iconRes = R.drawable.logo,
                    title = "A usuario de",
                    subtitle = "Wall-et",
                    onClick = {
                        navigateToScreen("transferTo", mapOf("target" to "usuario de Wall-et"))
                    }
                )
                Spacer(modifier = Modifier.width(adaptiveSpacing))
                TransferOptionCard(
                    iconRes = R.drawable.logo,
                    title = "A cuenta",
                    subtitle = "bancaria",
                    onClick = {
                        navigateToScreen("transferTo", mapOf("target" to "cuenta bancaria"))
                    }
                )
            }
        }


        item {
            Text(
                text = "Frecuentes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = adaptivePadding, bottom = adaptiveSpacing)
            )
        }
        items(getFrequentUsers()) { user ->
            FrequentUserRow(
                user = user,
                onClick = {navigateToScreen("transferAmount", mapOf("target" to user.contact))
                }
            )
            Spacer(modifier = Modifier.height(adaptiveSpacing))
        }

    }
}
fun getFrequentUsers(): List<User> {
    return listOf(
        User("Tomas", Icons.Default.AccountCircle, Icons.Default.Done, contact = "bordatomas@gmail.com"),
        User("Agustin", Icons.Default.AccountCircle, Icons.Default.Done, contact = "rondaagustin@gmail.com"),
        User("Lautaro", Icons.Default.AccountCircle, Icons.Default.Done, contact = "palettalautaro@gmail.com"),
        User("Valentin", Icons.Default.AccountCircle, Icons.Default.Done, contact = "garfivalentin@gmail.com")
    )
}

@Composable
fun FrequentUserRow(user: User,onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = user.profilePic,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .size(48.dp)
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = user.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = user.icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
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
        WindowWidthSizeClass.COMPACT -> adaptiveWidth = adaptiveWidth
        WindowWidthSizeClass.MEDIUM -> adaptiveWidth *= 2.3
        WindowWidthSizeClass.EXPANDED -> adaptiveWidth *= 4
    }
    when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> adaptiveHeight = (adaptiveHeight)
        WindowHeightSizeClass.MEDIUM -> adaptiveHeight = adaptiveWidth
        WindowHeightSizeClass.EXPANDED -> adaptiveHeight *= 2.5
    }
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
                    tint = MaterialTheme.colorScheme.primary,
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
    val profilePic: androidx.compose.ui.graphics.vector.ImageVector,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val contact : String = ""
)