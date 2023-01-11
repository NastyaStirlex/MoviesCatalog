package com.example.moviescatalog.screens.MovieScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.Body
import com.example.moviescatalog.ui.theme.BodyMontserrat
import com.example.moviescatalog.ui.theme.BrightWhite
import com.example.moviescatalog.ui.theme.GraySilver
import kotlinx.coroutines.channels.ticker

private const val WEIGHT_COLUMN_KEY = .3125f
private const val WEIGHT_COLUMN_VALUE = 1 - WEIGHT_COLUMN_KEY

@Composable
fun About(
    year: Int?,
    country: String?,
    duration: Int?,
    tagline: String?,
    producer: String?,
    budget: Int?,
    fees: Int?,
    age: Int?
) {
    Text(
        text = stringResource(R.string.about_film),
        style = Body,
        color = BrightWhite,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    InfoRow(key = "Год", value = year.toString())

    if(country != null) {
        InfoRow(key = "Страна", value = country)
    } else {
        InfoRow(key = "Страна", value = "-")
    }

    InfoRow(key = "Время", value = duration.toString() + " мин.")

    if(tagline != null) {
        InfoRow(key = "Слоган", value = tagline)
    } else {
        InfoRow(key = "Слоган", value = "-")
    }

    if(producer != null) {
        InfoRow(key = "Режиссёр", value = producer)
    } else {
        InfoRow(key = "Режиссёр", value = "-")
    }

    if(budget != null) {
        InfoRow(key = "Бюджет", value = "\$" + budget.toString())
    } else {
        InfoRow(key = "Бюджет", value = "-")
    }

    if(fees != null) {
        InfoRow(key = "Сборы в мире", value = "\$" + fees.toString())
    } else {
        InfoRow(key = "Сборы в мире", value = "-")
    }

    InfoRow(key = "Возраст", value = "$age+")
}

@Composable
fun InfoRow(
    key: String,
    value: String?
) {
    Row {
        Text(
            text = key,
            style = BodyMontserrat,
            color = GraySilver,
            modifier = Modifier.weight(WEIGHT_COLUMN_KEY)
        )
        if (value != null) {
            Text(
                text = value,
                style = BodyMontserrat,
                color = BrightWhite,
                modifier = Modifier.weight(WEIGHT_COLUMN_VALUE)
            )
        }
    }
}