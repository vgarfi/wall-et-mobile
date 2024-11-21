import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowHeightSizeClass
import com.example.wall_etmobile.R
import com.example.wall_etmobile.screens.cashflow.CashFlowBaseScaffold
import com.example.wall_etmobile.screens.cashflow.CashFlowStepIndicator
import com.example.wall_etmobile.screens.cashflow.CreditCardInfo
import com.example.wall_etmobile.screens.cashflow.TransferAmount
import com.example.wall_etmobile.screens.cashflow.TransferPayment
import com.example.wall_etmobile.screens.cashflow.TransferTo
import kotlinx.coroutines.launch

@Composable
fun TransferToScreen(
    navController: NavController,
    navigateToScreen: (String, Map<String, String?>) -> Unit = { _, _ -> },
    target: String?,
    page: Int?,
    contactName: String?,
    contactDetail: String?
) {
    var userId = remember { mutableStateOf("") }
    var message = remember { mutableStateOf("") }
    var selectedObject = remember { mutableStateOf<CreditCardInfo?>(null) }
    var amount = remember { mutableStateOf("0") }

    val totalSteps = 3
    var currentStep by remember { mutableIntStateOf(page ?: 0) }

    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { totalSteps }, initialPage = currentStep)

    val fixedStrings = when (target) {
        "user" -> listOf("Correo electronico", "usuario@gmail.com")
        "account" -> listOf("CBU/CVU", "Ingrese el CBU/CVU de la cuenta")
        else -> listOf("", "")
    }

    val pages = listOf(
        TransferTo(
            topPadding = calculateTopPadding(),
            hint = fixedStrings[1],
            header = fixedStrings[0],
            onValueChange = userId,
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        currentStep++
                        pagerState.animateScrollToPage(currentStep)
                    }
                }
            }
        ),
        TransferAmount(
            topPadding = calculateTopPadding(),
            contactDetails = userId.value,
            contactName = contactName ?: "Desconocido",
            onValueChange = message,
            onClick = {
                coroutineScope.launch {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        currentStep++
                        pagerState.animateScrollToPage(currentStep)
                    }
                }
            },
            onAmountChange = amount
        ),
        TransferPayment(
            topPadding = calculateTopPadding(),
            contactDetails = userId.value,
            contactName = contactName ?: "Desconocido",
            onValueChange = remember { mutableStateOf("") },
            message = message.value,
            cardsInfo = getSampleCards(),
            selectedObject = { selectedObject.value = it },
            amount = amount.value,
            buttonText = "Transferir",
            buttonEnabled = true,
            onClick = {
                currentStep = 0
                navigateToScreen("transaction-details", emptyMap())
            }
        )
    )

    CashFlowBaseScaffold(bigText = "Transferir", navController = navController) {
        CashFlowStepIndicator(
            currentStep = currentStep,
            totalSteps = totalSteps,
            modifier = Modifier.padding(top = calculateTopPadding().dp)
        ) {
            HorizontalPager(
                modifier = Modifier.padding(top = (calculateTopPadding() * 0.1).dp),
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                pages[page].invoke()
            }
        }
    }
}

@Composable
fun calculateTopPadding(): Int {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    return when (windowSizeClass.windowHeightSizeClass) {
        WindowHeightSizeClass.COMPACT -> 40
        WindowHeightSizeClass.MEDIUM -> 60
        WindowHeightSizeClass.EXPANDED -> 160
        else -> 1
    }
}

fun getSampleCards(): List<CreditCardInfo> {
    return listOf(
        CreditCardInfo(
            bankName = "Galicia",
            cardNumber = 4509909098989898,
            cardHolder = "Tomas Borda",
            cardExpiration = "07/25",
            cardImage = R.drawable.purple_card
        ),
        CreditCardInfo(
            bankName = "Santander",
            cardNumber = 5234567812345678,
            cardHolder = "Lucia Fernandez",
            cardExpiration = "03/26",
            cardImage = R.drawable.red_card
        )
    )
}
