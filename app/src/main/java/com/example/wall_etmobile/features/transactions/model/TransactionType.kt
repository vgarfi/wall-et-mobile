package com.example.wall_etmobile.features.transactions.model

import androidx.compose.ui.res.stringResource
import com.example.wall_etmobile.R

enum class TransactionType(val description: Int){
    INCOME(description = R.string.income_text),
    CHARGE(description = R.string.charge_text),
    TRANSFER(description = R.string.transfer_text)
}