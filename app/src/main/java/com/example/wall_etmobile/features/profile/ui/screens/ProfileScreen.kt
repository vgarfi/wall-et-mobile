package com.example.wall_etmobile.features.profile.ui.screens

import com.example.wall_etmobile.core.designKit.CustomTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
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
    ) { paddingValues ->
        BaseScaffold(tinyText = stringResource(R.string.your), bigText = stringResource(R.string.profile)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_picture),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(2.dp, MainPurple, CircleShape)
                )
                Spacer(modifier = Modifier.height(12.dp))
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
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = currentUser?.firstName + " " + currentUser?.lastName,
                            fontSize = 20.sp,
                            color = MainBlack
                        )
                        Text(
                            text = stringResource(R.string.member_since_july_9th),
                            fontSize = 12.sp,
                            color = GrayText
                        )
                    }
                }
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
                Spacer(modifier = Modifier.height(12.dp))
                CustomTextField(
                    label = stringResource(R.string.email),
                    hint = "",
                    controller = email,
                    enabled = false,
                    suffix = {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Edit,
                            contentDescription = "Edit Email",
                            tint = MainGrey,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = stringResource(R.string.change_password),
                    fontSize = 16.sp,
                    color = MainPurple,
                    textAlign = TextAlign.End,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.RESTOREPASSWORD.route)
                        }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(R.string.terms_and_conditions).trim()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    fontSize = 16.sp,
                    color = GrayText,
                    textDecoration = TextDecoration.Underline,
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
                        .fillMaxWidth().height(48.dp)
                        .padding(horizontal = 16.dp),
                    title = stringResource(R.string.logout),
                    elevation = true,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
