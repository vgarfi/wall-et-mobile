package com.example.wall_etmobile.features.profile.ui.screens

import android.annotation.SuppressLint
import com.example.wall_etmobile.core.designKit.CustomTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.designKit.BaseScaffold
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.core.theme.GrayText
import com.example.wall_etmobile.core.theme.MainBlack
import com.example.wall_etmobile.core.theme.MainGrey
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.features.auth.model.User
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Copy
import compose.icons.fontawesomeicons.solid.Edit
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.Random
import kotlin.math.abs

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    val currentUser = viewModel.getUserData()

    val cbu = remember { mutableStateOf(currentUser?.wallet?.cbu ?: "") }
    val email = remember { mutableStateOf(currentUser?.email ?: "") }

    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { _ ->
        BaseScaffold(tinyText = "", bigText = stringResource(R.string.profile)) {
            Column(
                modifier = Modifier.fillMaxSize().padding(14.dp).verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = painterResource(id = getProfileAvatarById(cbu.value.hashCode())),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, MainPurple, CircleShape)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "HCI Wallet Logo",
                        modifier = Modifier
                            .size(40.dp),
                        colorFilter = ColorFilter.tint(MainPurple)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = currentUser?.firstName + " " + currentUser?.lastName,
                            fontSize = 20.sp,
                            color = MainBlack
                        )
                        Text(
                            text = stringResource(R.string.wallet_member),
                            fontSize = 12.sp,
                            color = GrayText
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(0.2f))
                CustomTextField(
                    label = "CVU",
                    hint = "",
                    controller = cbu,
                    enabled = false,
                    suffix = {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Copy,
                            contentDescription = "Copy CVU",
                            tint = MainGrey,
                            modifier = Modifier
                                .size(16.dp)
                                .clickable {
                                    val textToCopy = cbu.value
                                    clipboardManager.setText(AnnotatedString(textToCopy))
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("CVU copied to clipboard")
                                    }
                                }
                        )
                    }
                )
                Spacer(modifier = Modifier.weight(0.2f))
                CustomTextField(
                    label = stringResource(R.string.email),
                    hint = "",
                    controller = email,
                    enabled = false,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.change_password),
                    fontSize = 14.sp,
                    color = MainPurple,
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.RESTOREPASSWORD.route)
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
                ActionButton(
                    onClick = {
                        viewModel.logout()
                        navController.navigate(Screen.LOGIN.route) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth().height(48.dp),
                    title = stringResource(R.string.logout),
                    elevation = true,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


fun getProfileAvatarById(id: Int?) : Int {
    val usedIndex = if (id == null) Random().nextInt() else abs(id)
    val avatars = listOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar6,
        R.drawable.avatar7,
        R.drawable.avatar8,
        R.drawable.avatar9,
        R.drawable.avatar10,
        R.drawable.avatar11,
        R.drawable.avatar12,
        R.drawable.avatar13,
        R.drawable.avatar14,
        R.drawable.avatar15,
        R.drawable.avatar16,
        R.drawable.avatar17,
        R.drawable.avatar18,
        R.drawable.avatar19,
        R.drawable.avatar20,
        R.drawable.avatar21,
        R.drawable.avatar22,
    )
    return avatars.elementAt(index = (usedIndex.rem(avatars.size)))
}