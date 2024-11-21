import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.DialogProperties
import com.example.wall_etmobile.design_kit.shared.CreditCard
import com.example.wall_etmobile.design_kit.shared.getBankFromCard
import com.example.wall_etmobile.design_kit.shared.getCardIcon
import com.example.wall_etmobile.design_kit.shared.isValidCardNumber
import com.example.wall_etmobile.ui.theme.MainGreen
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainRed
import kotlin.math.exp

@Composable
fun AddNewCreditCard(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onAddCard: (String, String, String) -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = true,
            )
            ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Información de la tarjeta",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Box(modifier = Modifier.height(16.dp))

                    var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
                    var cardHolder by remember { mutableStateOf(TextFieldValue("")) }

                    var cardNumberError by remember { mutableStateOf(false) }
                    var cardHolderError by remember { mutableStateOf(false) }

                    var expirationDate by remember { mutableStateOf("") }
                    var bankName by remember { mutableStateOf("")}

                    OutlinedTextField(
                        value = cardNumber,
                        onValueChange = {
                            cardNumber = it
                            cardNumberError = cardNumber.text.length != 16 || !cardNumber.text.all { char -> char.isDigit() }
                            bankName = getBankFromCard(cardNumber.text)
                        },
                        label = {
                            Text("Numero de tarjeta")
                        },
                        isError = cardNumberError,
                        leadingIcon = {
                            getCardIcon(cardNumber = cardNumber.text).invoke()
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (cardNumberError) MainRed else MainPurple,
                            cursorColor = if (cardNumberError) MainRed else MainPurple,
                            errorBorderColor = MainRed,
                        ),
                        trailingIcon = {
                            if (!cardNumberError && cardNumber.text.isNotEmpty()) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Validación",
                                    tint = MainGreen,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(modifier = Modifier.height(8.dp))

                    ExpirationDatePicker(
                        expirationDate = expirationDate,
                        onDateSelected = { date ->
                            expirationDate = date
                        }
                    )

                    Box(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = cardHolder,
                        maxLines = 1,
                        onValueChange = {
                            if (it.text.length <= 17) {
                                cardHolder = it
                                cardHolderError = cardHolder.text.isEmpty()
                            }
                            cardHolderError = cardHolder.text.isEmpty()
                        },
                        label = {
                            Text("Nombre del titular")
                        },
                        isError = cardHolderError,
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Person, contentDescription = "titular")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (cardHolderError) MainRed else MainPurple,
                            cursorColor = if (cardHolderError) MainRed else MainPurple,
                            errorBorderColor = MainRed,
                        ),
                        trailingIcon = {
                            if (!cardHolderError && cardHolder.text.isNotEmpty()) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Validación",
                                    tint = MainGreen,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Box(modifier = Modifier.height(16.dp))


                    if (cardNumber.text.isNotEmpty() && cardNumber.text.all { it.isDigit() }) {
                        CreditCard(
                            bankName = bankName,
                            cardNumber = cardNumber.text.toBigInteger(),
                            cardHolder = cardHolder.text,
                            cardExpiration = expirationDate,
                            cardImage = com.example.wall_etmobile.R.drawable.purple_card
                        )
                    } else {
                        CreditCard(
                            bankName = bankName,
                            cardNumber = 1234000000000000,
                            cardHolder = cardHolder.text,
                            cardExpiration = expirationDate,
                            cardImage = com.example.wall_etmobile.R.drawable.purple_card
                        )
                    }
                    Button(
                        onClick = {
                            onAddCard(cardNumber.text, expirationDate, bankName)
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MainPurple)
                    ) {
                        Text(text = "Agregar tarjeta", color = Color.White)
                    }
                }
            }
        }
    }
}
