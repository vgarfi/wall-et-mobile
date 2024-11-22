package com.example.wall_etmobile.features.home.ui.screens

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wall_etmobile.R
import com.example.wall_etmobile.core.theme.MainWhite
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.sp
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.wall_etmobile.ui.data.FavoriteTileData
import com.example.wall_etmobile.ui.data.MovementData
import com.example.wall_etmobile.ui.data.RoundedImageData
import com.example.wall_etmobile.features.home.ui.designKit.CvuDialog
import com.example.wall_etmobile.features.home.ui.designKit.FavoriteDialog
import com.example.wall_etmobile.features.home.ui.designKit.HomeHeader
import com.example.wall_etmobile.features.home.ui.designKit.MountVisor
import com.example.wall_etmobile.features.home.ui.designKit.SectionButtons
import com.example.wall_etmobile.core.designKit.CircularAddButton
import com.example.wall_etmobile.core.designKit.MovementTileList
import com.example.wall_etmobile.core.designKit.RoundedImage
import com.example.wall_etmobile.core.designKit.RoundedImageWithText
import com.example.wall_etmobile.core.designKit.TitleWithTextButton
import com.example.wall_etmobile.core.designKit.TransactionType
import com.example.wall_etmobile.core.navigation.NavigatorWrapper
import com.example.wall_etmobile.core.navigation.Screen

@Composable
fun HomeScreen(navWrapper: NavigatorWrapper, adaptiveInfo: WindowAdaptiveInfo) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    val isRotated = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE  //si es verdadero esta rotada

    var showCvu by rememberSaveable { mutableStateOf(false) }
    var showMoney by rememberSaveable { mutableStateOf(true) }
    var showFavoriteModal by rememberSaveable { mutableStateOf(false) }

    val clipboardManager = LocalClipboardManager.current

    val cvu = "12321312312233123"
    val username = "Valentin"

    var tileHeight = (screenHeight*0.055).dp
    var titleSize = (screenHeight*0.02).sp
    var subTitleSize = (screenHeight*0.015).sp
    var mountSize = (screenHeight*0.03).sp
    val onMovementClick = { navWrapper.navigateToDetailsFromHome() }

    val contactsSize = (screenHeight * 0.08).dp

    val contacts = listOf(
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize),
        RoundedImageData(painter = null, title = "Tomas", size = contactsSize)
    )

    val favoriteHeight = (screenHeight*0.055).dp
    val favoriteTileTitleSize = (screenHeight*0.02).sp
    val favoriteTileBankSize = (screenHeight*0.015).dp

    val favorites = listOf(
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = true,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = false,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = true,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = false,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = true,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = false,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = true,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = false,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = true,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = false,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = true,
            onClick = {

            }
        ),
        FavoriteTileData(
            tileHeight = favoriteHeight,
            title = "Agustin",
            titleSize = favoriteTileTitleSize,
            bankAccounts = listOf(
                { RoundedImage(size = favoriteTileBankSize) },
                { RoundedImage(size = favoriteTileBankSize) }
            ),
            userImage = { RoundedImage(size = favoriteHeight) },
            isFavorite = false,
            onClick = {

            }
        )
    )

    val onAddFavoriteButton = {
        showFavoriteModal = true
    }

    if (isRotated || adaptiveInfo.windowSizeClass.windowWidthSizeClass != WindowWidthSizeClass.COMPACT) {
        tileHeight = (screenHeight*0.15).dp
        titleSize = (screenHeight*0.05).sp
        subTitleSize = (screenHeight*0.04).sp
        mountSize = (screenHeight*0.04).sp

        val movements = listOf(
            MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MainWhite)
        ) {
            Column(
            ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((screenHeight * 0.45).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home_header),
                        contentDescription = "Fondo",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter
                    )
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        HomeHeader(onClick = { showCvu = !showCvu })
                        MountVisor(
                            mount = 12672.68,
                            onClick = { showMoney = !showMoney },
                            showMoney = showMoney
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .width((screenWidth * 0.5).dp)
                            .padding(8.dp)
                    ) {
                        TitleWithTextButton(
                            title = R.string.last_transactions,
                            textButton = R.string.see_moore,
                            onClick = { navWrapper.navigate(Screen.TRANSACTIONS) }
                        )

                        MovementTileList(movements = movements)

                    }
                    Box(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        SectionButtons(navWrapper = navWrapper, height = (screenHeight * 0.3).dp)
                    }
                }
            }
        }
    }
    else {
        val movements = listOf(
            MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            ), MovementData(
                tileHeight = tileHeight,
                title = R.string.charge_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.CHARGE,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.transfer_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 60.00,
                mountSize = mountSize,
                transactionType = TransactionType.TRANSFER,
                onClick = onMovementClick
            ),
            MovementData(
                tileHeight = tileHeight,
                title = R.string.income_text,
                titleSize = titleSize,
                subTitle = "19:12 a Tomas",
                subTitleSize = subTitleSize,
                mount = 111.00,
                mountSize = mountSize,
                transactionType = TransactionType.INCOME,
                onClick = onMovementClick
            )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MainWhite)
        ) {
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
                    .padding(30.dp)
            ) {
                HomeHeader(onClick = { showCvu = !showCvu })
                MountVisor(
                    mount = 12672.68,
                    onClick = { showMoney = !showMoney },
                    showMoney = showMoney
                )
                Spacer(modifier = Modifier.padding(vertical = 16.dp))
                SectionButtons(navWrapper = navWrapper, height = (screenHeight * 0.1).dp)
                Spacer(modifier = Modifier.padding(vertical = 16.dp))

                Column(
                    modifier = Modifier
                        .height((screenHeight * 0.18).dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.favorites),
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                        fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        CircularAddButton(
                            size = contactsSize,
                            onClick = onAddFavoriteButton,
                            text = stringResource(R.string.add)
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        LazyRow {
                            items(items = contacts) {
                                RoundedImageWithText(size = it.size, title = it.title, painter = it.painter)
                                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                            }
                        }
                        Spacer(modifier = Modifier.padding(vertical = 2.dp))

                    }

                }

                Column(
                    modifier = Modifier
                        .height((screenHeight * 0.6).dp)
                        .fillMaxWidth()
                ) {
                    TitleWithTextButton(
                        title = R.string.last_transactions,
                        textButton = R.string.see_moore,
                        onClick = { navWrapper.navigate(Screen.TRANSACTIONS) }
                    )
                    MovementTileList(movements = movements)
                }
            }
        }
        if (showFavoriteModal){
            FavoriteDialog(
                onDismissRequest = { showFavoriteModal = false },
                height = (screenHeight * 0.6).dp,
                favorites = favorites
            )
        }
    }
    if (showCvu) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            CvuDialog(
                onDismissRequest = { showCvu = false },
                onCopyCvu = { clipboardManager.setText(AnnotatedString(cvu)) },
                cvu = cvu,
                username = username
            )
        }
    }
}

