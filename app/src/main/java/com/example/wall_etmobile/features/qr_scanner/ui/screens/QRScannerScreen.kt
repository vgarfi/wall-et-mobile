package com.example.wall_etmobile.features.qr_scanner.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainRed
import com.example.wall_etmobile.core.theme.MainWhite
import com.example.wall_etmobile.features.cashflow.ui.composables.CashFlowTitleRow
import com.journeyapps.barcodescanner.CompoundBarcodeView
import com.journeyapps.barcodescanner.DecoratedBarcodeView
@Composable
fun QrScannerScreen(
    onQrCodeScanned: (String) -> Unit,
    onError: (String) -> Unit,
    onBack: () -> Unit
    ) {
    val context = LocalContext.current
    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted -> hasPermission = isGranted }
    )

    LaunchedEffect(Unit) {
        if (!hasPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainWhite)
    ) {

        if (hasPermission) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->

                    CompoundBarcodeView(ctx).apply {
                        this.statusView.text = ctx.getString(R.string.scan_qr_info)
                        this.decodeContinuous { result ->
                            result.text?.let {
                                onQrCodeScanned(it)
                            } ?: onError("QR inválido")
                        }
                    }
                },
                update = { view ->
                    view.resume()
                }
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.need_camera),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainRed
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.qr_banner),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter
        )
        CashFlowTitleRow(bigText = stringResource(R.string.scan_qr), navController = NavController(context = context), onBack)

    }
}
