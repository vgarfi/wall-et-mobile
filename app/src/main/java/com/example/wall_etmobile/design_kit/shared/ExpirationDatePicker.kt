import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.wall_etmobile.design_kit.shared.getCardIcon
import com.example.wall_etmobile.design_kit.shared.isValidCardNumber
import com.example.wall_etmobile.ui.theme.MainGreen
import com.example.wall_etmobile.ui.theme.MainPurple
import com.example.wall_etmobile.ui.theme.MainRed
import kotlin.math.exp

@Composable
fun ExpirationDatePicker(
    expirationDate: String,
    onDateSelected: (String) -> Unit
) {
    var isError by remember { mutableStateOf(false) }
    var expandedMonth by remember { mutableStateOf(false) }
    var expandedYear by remember { mutableStateOf(false) }
    var selectedMonth by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("") }

    val months = (1..12).map { it.toString().padStart(2, '0') }
    val years = (2024..2034).map { it.toString().substring(2) }
    var dateText by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(selectedMonth, selectedYear) {
        if (selectedMonth.isNotEmpty() && selectedYear.isNotEmpty()) {
            dateText = TextFieldValue("$selectedMonth/$selectedYear")
        }
    }

    var cardNumber by remember { mutableStateOf(TextFieldValue("")) }
    var cardNumberError by remember { mutableStateOf(false) }
    var expirationDate by remember { mutableStateOf("") }
    var bankName by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.clickable {  })
    OutlinedTextField(
        value = dateText,
        onValueChange = {
            if (selectedMonth.isEmpty()) {
                expandedMonth = true
            } else if (selectedYear.isEmpty()) {
                expandedYear = true
            }
        },
        label = {
            Text(if (isError) "Error: Fecha inválida" else "Fecha de expiración")
        },
        isError = isError,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Seleccionar fecha",
                modifier = Modifier.size(24.dp)
            )
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
        placeholder = { Text("MM/YY") },

        modifier = Modifier
            .clickable {
                expandedMonth = true
                expandedYear = true
            }
            .fillMaxWidth()
    )

    if (expandedMonth) {
        Dialog(
            onDismissRequest = { expandedMonth = false }
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Selecciona el mes", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    months.forEach { month ->
                        TextButton(
                            onClick = {
                                selectedMonth = month
                                expandedMonth = false
                                // Activar la selección de año después de elegir el mes
                                expandedYear = true
                            }
                        ) {
                            Text(text = month)
                        }
                    }
                }
            }
        }
    }

    if (expandedYear) {
        Dialog(
            onDismissRequest = { expandedYear = false },
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Selecciona el año", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    years.forEach { year ->
                        TextButton(
                            onClick = {
                                selectedYear = year
                                expandedYear = false
                                onDateSelected("$selectedMonth/$selectedYear")
                            }
                        ) {
                            Text(text = year)
                        }
                    }
                }
            }
        }
    }

    if (isError) {
        Text(
            text = "Debe seleccionar un mes y un año válido.",
            color = MainRed,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}