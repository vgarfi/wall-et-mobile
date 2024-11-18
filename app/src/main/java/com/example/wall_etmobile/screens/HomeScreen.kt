package com.example.wall_etmobile.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.design_kit.home.HomeHeader
import com.example.wall_etmobile.design_kit.home.SectionButton
import com.example.wall_etmobile.ui.theme.MainWhite
import com.pathak.barberapp.navigation.Screen
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.regular.Eye

@Composable
fun HomeScreen(navController: NavController) {
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
                .padding(16.dp)
                .padding(top = 1.dp)
        ) {
            HomeHeader()
            Box(modifier = Modifier.height(60.dp))
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Tu dinero disponible", color = MainWhite, fontWeight = FontWeight.W400, fontSize = 17.5.sp)
                Box(modifier = Modifier.height(height = 6.dp))
                Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(modifier = Modifier.weight(2f))
                Text(text = "$12,672.90", color = MainWhite, fontWeight = FontWeight.W900, fontSize = 47.5.sp)
                Box(modifier = Modifier.width(12.dp))
                Icon(imageVector = FontAwesomeIcons.Regular.Eye, contentDescription = "ocultar", tint = MainWhite, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.weight(1f))
            }
            }
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
                Row (
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
}