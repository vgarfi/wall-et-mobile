package com.example.wall_etmobile.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.wall_etmobile.R
import com.example.wall_etmobile.ui.theme.MainWhite
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.example.wall_etmobile.design_kit.home.SectionButton
import com.pathak.barberapp.navigation.Screen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Copy
import compose.icons.fontawesomeicons.regular.Eye
import compose.icons.fontawesomeicons.regular.EyeSlash


@Composable
fun CvuDialog(onDismissRequest: () -> Unit, onCopyCvu: (text:String) -> Unit) {
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Hola Valentin!",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Text(
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = "Tu CVU:"
                )
                ElevatedCard (
                    colors = CardDefaults.elevatedCardColors(MaterialTheme.colorScheme.surface),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            color = MaterialTheme.colorScheme.onSurface,
                            text = "12345678901234567890",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                        )
                        IconButton(
                            onClick = {
                                onCopyCvu("12345678901234567890")
                            }
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Regular.Copy,
                                contentDescription = "Copy CVU",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                    }
                }
                Text(
                    text = "Tu cvu es unico e identifica tu cuenta Wall-et",
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun MoneyVisor(money:String, showMoney: Boolean, onClick: () -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Dinero disponible",
            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Row {
            if (showMoney) {
                Text(
                    text = "$ ${money}",
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                IconButton(onClick = { onClick() }) {
                    Icon(
                        imageVector = FontAwesomeIcons.Regular.Eye,
                        contentDescription = "Eye",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                Text(
                    text = "$ ·······",
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                IconButton(onClick = { onClick() }) {
                    Icon(
                        imageVector = FontAwesomeIcons.Regular.EyeSlash,
                        contentDescription = "EyeSlash",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun Header(onClick: () -> Unit){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Wall-et",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        )
        ElevatedButton(
            modifier = Modifier
                .align(Alignment.CenterVertically),
            onClick = {
                onClick()
            }
        ) {
            Text(
                text = "Tu informacion",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}



@Composable
fun HomeScreen(navController: NavController) {
    var showCvu by rememberSaveable { mutableStateOf(false) }
    var showMoney by rememberSaveable { mutableStateOf(true) }
    val clipboardManager = LocalClipboardManager.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MainWhite)) {
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
                .padding(30.dp)
        ) {
            Header(onClick = { showCvu = !showCvu })
            MoneyVisor(
                money = "12672.68",
                onClick = { showMoney = !showMoney },
                showMoney = showMoney
            )
            
            Box(modifier = Modifier.height(15.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = MainWhite,
                shadowElevation = 5.dp,
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight().padding(horizontal = 25.dp)
                ) {
                    SectionButton(
                        title = "Transferir",
                        icon = R.drawable.transfer_icon,
                        onClick = {
                            navController.navigate(Screen.Transfer.route) {
                                popUpTo(Screen.Home.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    )
                    VerticalDivider(
                        modifier = Modifier.padding(vertical = 20.dp),
                        color = Color(0xFFE8E8E8)
                    )
                    SectionButton(title = "Cobrar", icon = R.drawable.charge_icon,
                        onClick = {
                            navController.navigate(Screen.Charge.route) {
                                popUpTo(Screen.Home.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    )
                    VerticalDivider(
                        modifier = Modifier.padding(vertical = 20.dp),
                        color = Color(0xFFE8E8E8)
                    )
                    SectionButton(title = "Ingresar", icon = R.drawable.enter_icon,
                        onClick = {
                            navController.navigate(Screen.Enter.route) {
                                popUpTo(Screen.Home.route) { inclusive = false }
                                launchSingleTop = true
                            }
                        })
                }
            }
        }
    }
    if (showCvu) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom
        ) {
            CvuDialog(
                onDismissRequest = { showCvu = false },
                onCopyCvu = { clipboardManager.setText(AnnotatedString(it)) }
            )
        }
    }
}


