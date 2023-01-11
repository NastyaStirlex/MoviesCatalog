package com.example.moviescatalog.screens.SignInScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.*

@Composable
fun SignInScreen(
    onClickLogin: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val login = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // logo
    Box(
        modifier = Modifier
            .padding(top = 64.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = null,
            modifier = Modifier
                .size(250.dp, 170.dp)
        )
    }
    // поля для ввода
    Box(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 250.dp)
            .wrapContentHeight(),
    ) {
        Column() {
            // login
            OutlinedTextField(
                value = login.value,
                onValueChange = { login.value = it },
                textStyle = BodySmall,
                placeholder = {
                    Text(
                        stringResource(R.string.text_field_login),
                        style = BodySmall,
                        color = GrayFaded,
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Accent,
                    unfocusedBorderColor = GraySilver,
                    focusedBorderColor = Accent,
                    placeholderColor = GraySilver,
                    cursorColor = Accent,
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            //password
            OutlinedTextField(
                value = password.value,
                textStyle = BodySmall,
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                onValueChange = { password.value = it },
                placeholder = {
                    Text(
                        stringResource(R.string.text_field_pass),
                        style = BodySmall,
                        color = GrayFaded,
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = Accent,
                    unfocusedBorderColor = GraySilver,
                    focusedBorderColor = Accent,
                    placeholderColor = GraySilver,
                    cursorColor = Accent,
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { onClickLogin() },
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp),
                border = if (true) BorderStroke(1.dp, Accent) else BorderStroke(
                    1.dp,
                    GraySilver
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Accent,
                    contentColor = BrightWhite,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Accent
                )
            ) {
                Text(stringResource(R.string.out_button_login), style = Body)
            }
            TextButton(
                onClick = onRegisterClick,
                modifier = Modifier
                    .height(39.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    stringResource(R.string.button_logup),
                    color = Accent,
                    style = Body,
                )
            }
        }
    }
}