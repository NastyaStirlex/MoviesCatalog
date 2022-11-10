package com.example.moviescatalog.screens.SignInScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moviescatalog.R
import com.example.moviescatalog.navigation.Screens

@Composable
fun SignInScreen(onLoginClick: () -> Unit, onRegisterClick: () -> Unit) {
    val login = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically),
                contentAlignment = Alignment.TopCenter
        ) {
            Column() {
                Image(
                    painter = painterResource(R.drawable.icon),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 64.dp, start = 20.dp)
                )
                Image(painter = painterResource(R.drawable.appname), contentDescription = null, modifier = Modifier
                    .padding(top = 20.dp))
            }
        }
        Box(modifier = Modifier
            .padding(horizontal = 18.dp)
            .padding(top = 250.dp)
            .wrapContentHeight(),
        ) {
            Column(modifier = Modifier) {
                OutlinedTextField(
                    value = login.value,
                    onValueChange = { login.value = it },
                    placeholder = { Text(stringResource(R.string.text_field_login)) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color(0xffEF3A01),
                        unfocusedBorderColor = Color(0xffB7B7B7),
                        focusedBorderColor = Color(0xffEF3A01),
                        placeholderColor = Color(0xffB7B7B7),
                        cursorColor = Color(0xffEF3A01),
                    ),
                    shape = RoundedCornerShape(9.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(18.dp))
                OutlinedTextField(
                    value = password.value,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = { password.value = it },
                    placeholder = { Text(stringResource(R.string.text_field_pass)) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color(0xffEF3A01),
                        unfocusedBorderColor = Color(0xffB7B7B7),
                        focusedBorderColor = Color(0xffEF3A01),
                        placeholderColor = Color(0xffB7B7B7),
                        cursorColor = Color(0xffEF3A01),
                    ),
                    shape = RoundedCornerShape(9.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
            contentAlignment = Alignment.BottomCenter) {
            Column(modifier = Modifier
            ) {
                OutlinedButton(onClick = onLoginClick, modifier = Modifier
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, Color(0xffB7B7B7))
                ) {
                    Text(stringResource(R.string.out_button_login), color = Color(0xffEF3A01))
                }
                TextButton(onClick = onRegisterClick, modifier = Modifier
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(stringResource(R.string.button_logup), color = Color(0xffEF3A01))
                }
            }
        }
    }

}