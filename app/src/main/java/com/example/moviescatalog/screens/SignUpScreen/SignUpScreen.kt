package com.example.moviescatalog.screens.SignUpScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun SignUpScreen(onRegisterClick: () -> Unit, onLoginClick: () -> Unit) {
    var selectedMan by remember {mutableStateOf(false)}
    var selectedWoman by remember {mutableStateOf(false)}
    val login = remember{ mutableStateOf("") }
    val email = remember{ mutableStateOf("") }
    val name = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }
    val check_password = remember{ mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically),
            contentAlignment = Alignment.TopCenter
        ) {
                Column(modifier = Modifier) {
                    Image(
                        painter = painterResource(com.example.moviescatalog.R.drawable.icon),
                        contentDescription = null,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(160.dp, 138.dp)
                            .padding(top = 64.dp, start = 7.dp)

                    )
                    Image(
                        painter = painterResource(com.example.moviescatalog.R.drawable.appname),
                        contentDescription = null,
                        modifier = Modifier
                            .size(168.dp, 41.dp)
                            .padding(top = 15.dp)
                    )
                }
            }

        Box(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(top = 204.dp)
                .wrapContentHeight(),
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(22.dp)) {
                item(){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(18.dp), modifier = Modifier
                    ) {
                        Text(
                            stringResource(com.example.moviescatalog.R.string.text_signup),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xffEF3A01),
                            modifier = Modifier
                        )
                        OutlinedTextField(
                            value = login.value,
                            onValueChange = { login.value = it },
                            singleLine = true,
                            placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_login)) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xffEF3A01),
                                unfocusedBorderColor = Color(0xffB7B7B7),
                                focusedBorderColor = Color(0xffEF3A01),
                                disabledPlaceholderColor = Color(0xffD1D1D1),
                                cursorColor = Color(0xffEF3A01),
                            ),
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = email.value,
                            onValueChange = { email.value = it },
                            singleLine = true,
                            placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_email)) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xffEF3A01),
                                unfocusedBorderColor = Color(0xffB7B7B7),
                                focusedBorderColor = Color(0xffEF3A01),
                                disabledPlaceholderColor = Color(0xffD1D1D1),
                                cursorColor = Color(0xffEF3A01),
                            ),
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = name.value,
                            onValueChange = { name.value = it },
                            singleLine = true,
                            placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_name)) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xffEF3A01),
                                unfocusedBorderColor = Color(0xffB7B7B7),
                                focusedBorderColor = Color(0xffEF3A01),
                                disabledPlaceholderColor = Color(0xffD1D1D1),
                                cursorColor = Color(0xffEF3A01),
                            ),
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = password.value,
                            onValueChange = { password.value = it },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_pass)) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xffEF3A01),
                                unfocusedBorderColor = Color(0xffB7B7B7),
                                focusedBorderColor = Color(0xffEF3A01),
                                disabledPlaceholderColor = Color(0xffD1D1D1),
                                cursorColor = Color(0xffEF3A01),
                            ),
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = check_password.value,
                            onValueChange = { check_password.value = it },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_check_pass)) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xffEF3A01),
                                unfocusedBorderColor = Color(0xffB7B7B7),
                                focusedBorderColor = Color(0xffEF3A01),
                                disabledPlaceholderColor = Color(0xffD1D1D1),
                                cursorColor = Color(0xffEF3A01),
                            ),
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        OutlinedTextField(
                            readOnly = true,
                            value = "",
                            onValueChange = {},
                            placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_birthdate)) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = Color(0xffEF3A01),
                                unfocusedBorderColor = Color(0xffB7B7B7),
                                focusedBorderColor = Color(0xffEF3A01),
                                disabledPlaceholderColor = Color(0xffD1D1D1),
                                cursorColor = Color(0xffEF3A01),
                            ),
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                            .fillMaxWidth()
                        ) {
                            OutlinedButton(onClick = { if (selectedWoman) {selectedMan = true; selectedWoman = false} else selectedMan = true },
                                shape = RoundedCornerShape(
                                    topStart = 9.dp,
                                    topEnd = 0.dp,
                                    bottomStart = 9.dp,
                                    bottomEnd = 0.dp
                                ),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (selectedMan) Color(0xffEF3A01) else Color(0xff150D0B),
                                    contentColor = if (selectedMan) Color(0xffFFFFFF) else Color(0xffD1D1D1)),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(stringResource(com.example.moviescatalog.R.string.sex_man))
                            }
                            OutlinedButton(onClick = { if (selectedMan) {selectedWoman = true; selectedMan = false} else selectedWoman = true},
                                shape = RoundedCornerShape(topStart = 0.dp,
                                    topEnd = 9.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 9.dp
                                ), colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (selectedWoman) Color(0xffEF3A01) else Color(0xff150D0B),
                                    contentColor = if (selectedWoman) Color(0xffFFFFFF) else Color(0xffD1D1D1)),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(stringResource(com.example.moviescatalog.R.string.sex_woman))
                            }
                        }
                    }
                }
                item() {
                    Column() {
                        OutlinedButton(
                            onClick = onRegisterClick, modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(1.dp, Color(0xffB7B7B7))
                        ) {
                            Text(
                                stringResource(com.example.moviescatalog.R.string.button_signup),
                                color = Color(0xffEF3A01)
                            )
                        }
                        TextButton(
                            onClick = onLoginClick, modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                stringResource(com.example.moviescatalog.R.string.button_have_account_yet),
                                color = Color(0xffEF3A01)
                            )
                        }
                    }
                }
            }
        }
    }
}

