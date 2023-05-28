package com.example.findingfalcon.ui

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.findingfalcon.R

enum class Screen(@StringRes val title: Int) {
    Destination1(title = R.string.destination1),
    Destination2(title = R.string.destination2),
    Destination3(title = R.string.destination3),
    Destination4(title = R.string.destination4),
    Result(title = R.string.find_falcone)
}