package com.example.wall_etmobile.features.cashflow.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.features.cashflow.ui.screens.getFrequentUsers
import com.example.wall_etmobile.features.profile.ui.screens.getProfileAvatarById

@Composable
fun ContactTransferTile (
    icon: Int,
    contactName: String,
    contactDetails: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(58.dp)
                .clip(CircleShape)
                .background(MainWhite)
                .border(
                    border = BorderStroke(1.5.dp, MainPurple),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id =  getFrequentUsers()
                    .find { it.contact == contactName }
                    ?.profilePic ?: getProfileAvatarById(contactName.hashCode())),
                contentDescription = "icono",

                modifier = Modifier.padding(7.dp).fillMaxSize().clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = getFrequentUsers()
                .find { it.contact == contactName }
                ?.name ?: contactName, fontWeight = FontWeight.W600)
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "icono",
                    modifier = Modifier.size(15.dp),
                    tint = MainPurple
                )
                Box(modifier = Modifier.width(3.dp))
                Text(text = contactDetails, color = Color.Gray)
            }
        }
    }
}