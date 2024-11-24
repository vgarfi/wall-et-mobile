package com.example.wall_etmobile.features.auth.ui.screens

import androidx.activity.ComponentActivity
import com.example.wall_etmobile.core.designKit.CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wall_etmobile.MyApplication
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.designKit.ActionButton
import com.example.wall_etmobile.core.navigation.Screen
import com.example.wall_etmobile.features.auth.ui.composables.TermsCheckbox
import com.example.wall_etmobile.core.theme.MainPurple
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.features.auth.viewmodel.AuthViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {

    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val errorMsg =
        stringResource(R.string.an_error_ocurred_while_trying_to_sign_up_please_check_that_the_information_is_correct)
    val emailNotValidMsg = stringResource(R.string.the_email_is_not_valid)
    val passwordsNotMatchMsg = stringResource(R.string.passwords_do_not_match)
    val fullNameMsg = stringResource(R.string.please_enter_your_full_name)
    val shortPasswordMsg = stringResource(R.string.the_password_must_be_at_least_8_characters_long)
    val termsMsg =
        stringResource(R.string.in_order_to_continue_you_must_accept_the_terms_and_conditions)

    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val acceptedTerms = remember { mutableStateOf(false) }

    return Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = MainWhite)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 40.dp)
            ) {
                Spacer(modifier = Modifier.size(24.dp))
                CustomTextField(
                    label = stringResource(R.string.email),
                    hint = "juan@gmail.com",
                    controller = email,
                )
                Spacer(modifier = Modifier.size(24.dp))
                CustomTextField(
                    label = stringResource(R.string.full_name),
                    hint = "Juan Pérez",
                    controller = name,
                )
                Spacer(modifier = Modifier.size(24.dp))
                CustomTextField(
                    label = stringResource(R.string.password),
                    hint = stringResource(R.string.at_least_8_characters),
                    controller = password,
                    isPassword = true,
                )
                Spacer(modifier = Modifier.size(24.dp))
                CustomTextField(
                    label = stringResource(R.string.confirm_your_password),
                    hint = stringResource(R.string.confirm_your_password),
                    controller = confirmPassword,
                    isPassword = true,
                )
                Spacer(modifier = Modifier.size(24.dp))
                ActionButton(
                    onClick = {
                        if (!acceptedTerms.value) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(termsMsg)
                            }
                            return@ActionButton
                        }

                        // Email regex
                        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
                        if (!email.value.matches(emailRegex.toRegex())) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(emailNotValidMsg)
                            }
                            return@ActionButton
                        }

                        if (password.value == "" || password.value != confirmPassword.value) {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(passwordsNotMatchMsg)
                            }
                            return@ActionButton
                        }

                        if(name.value.trim().split(" ").size < 2){
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(fullNameMsg)
                            }
                            return@ActionButton
                        }

                        if(password.value.length < 8){
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(shortPasswordMsg)
                            }
                            return@ActionButton
                        }

                        val firstName = name.value.trim().split(" ")[0]
                        val lastName = name.value.trim().split(" ")[1]
                        viewModel.register(firstName, lastName, email.value, password.value)
                        val modified = viewModel.getUserRegisterEmail() != null
                        if (modified) {
                            navController.navigate(Screen.VERIFYACCOUNT.route)
                        } else {
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar(errorMsg)
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    title = stringResource(R.string.create_account),
                    elevation = true,
                )
                Spacer(modifier = Modifier.size(16.dp))
                TermsCheckbox(
                    onTap = {
                        value -> acceptedTerms.value = value
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    onClick = {
                        navController.navigate(Screen.LOGIN.route)
                    },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(R.string.already_have_an_account),
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = stringResource(R.string.sign_in),
                        fontSize = 16.sp,
                        color = MainPurple
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }

    }
}
