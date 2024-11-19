package com.example.wall_etmobile.design_kit.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.ui.theme.MainBlack
import com.example.wall_etmobile.ui.theme.MainWhite
import com.pathak.barberapp.navigation.Screen

@Composable
fun HomeHeader(
    navController: NavController
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
    ){
        Row {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
            )
            Box(Modifier.width(12.dp))
            Text(
                text = "Wall-et",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFFD3D0D6)
                )
        }
        Spacer(modifier =Modifier.weight(1f))
        Box (
            modifier = Modifier
                .background(color = MainWhite, shape = RoundedCornerShape(20.dp))
                .padding(horizontal = 20.dp, vertical = 3.dp)
                .clickable {
                    navController.navigate(Screen.TransactionDetails.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                        launchSingleTop = true
                    }
                }
        ){
           Row(
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center
           ) {
               Text("Tu ", color = MainBlack, fontSize = 15.sp)
               Text("información", color = MainBlack, fontWeight = FontWeight.Bold, fontSize = 15.sp)
           }
        }
    }
}