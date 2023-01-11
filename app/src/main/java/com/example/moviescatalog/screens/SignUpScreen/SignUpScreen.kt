package com.example.moviescatalog.screens.SignUpScreen

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.*
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

@Composable
fun SignUpScreen(
    onGoToMainClick: () -> Unit,
    onAccountClick: () -> Unit
) {
    val login = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val checkPassword = remember { mutableStateOf("") }


    var selectedMan by rememberSaveable { mutableStateOf(false) }
    var selectedWoman by remember { mutableStateOf(false) }

    val mDate = remember { mutableStateOf(LocalDate.now()) }

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
                .size(147.41.dp, 100.dp)
        )
    }

    // поля для ввода
    Box(
        modifier = Modifier
            .padding(16.dp)
            .padding(top = 204.dp)
            .wrapContentHeight(),
    ) {
        LazyColumn() {
            item() {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        stringResource(R.string.text_signup),
                        style = H1,
                        color = Accent
                    )

                    OutlinedTextField(
                        value = login.value,
                        onValueChange = { login.value = it },
                        singleLine = true,
                        placeholder = {
                            Text(
                                stringResource(R.string.text_field_login),
                                style = BodySmall
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Accent,
                            unfocusedBorderColor = GraySilver,
                            focusedBorderColor = Accent,
                            disabledPlaceholderColor = GrayFaded,
                            cursorColor = Accent,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        shape = RoundedCornerShape(9.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        singleLine = true,
                        placeholder = {
                            Text(
                                stringResource(R.string.text_field_email),
                                style = BodySmall
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Accent,
                            unfocusedBorderColor = GraySilver,
                            focusedBorderColor = Accent,
                            disabledPlaceholderColor = GrayFaded,
                            cursorColor = Accent,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        shape = RoundedCornerShape(9.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        singleLine = true,
                        placeholder = {
                            Text(
                                stringResource(R.string.text_field_name),
                                style = BodySmall
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Accent,
                            unfocusedBorderColor = GraySilver,
                            focusedBorderColor = Accent,
                            disabledPlaceholderColor = GrayFaded,
                            cursorColor = Accent,
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
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
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        placeholder = {
                            Text(
                                stringResource(R.string.text_field_pass),
                                style = BodySmall
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Accent,
                            unfocusedBorderColor = GraySilver,
                            focusedBorderColor = Accent,
                            disabledPlaceholderColor = GrayFaded,
                            cursorColor = Accent,
                        ),
                        shape = RoundedCornerShape(9.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = checkPassword.value,
                        onValueChange = { checkPassword.value = it },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        placeholder = {
                            Text(
                                stringResource(R.string.text_field_check_pass),
                                style = BodySmall
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = Accent,
                            unfocusedBorderColor = GraySilver,
                            focusedBorderColor = Accent,
                            disabledPlaceholderColor = GrayFaded,
                            cursorColor = Accent,
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    //birthdate
                    val defaultPickerDate = LocalDate.now()

                    val mDatePickerDialog = DatePickerDialog(
                        LocalContext.current,
                        { _, year, month, dayOfMonth ->
                            mDate.value = LocalDate.of(year, month + 1, dayOfMonth)
                        },
                        defaultPickerDate.year,
                        defaultPickerDate.monthValue - 1,
                        defaultPickerDate.dayOfMonth
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .border(1.dp, GraySilver, RoundedCornerShape(8.dp))
                            .clickable { mDatePickerDialog.show() },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Text(
                            text = formatter.format(mDate.value),
                            style = BodySmall,
                            color = Accent,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(start = 16.dp)
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(end = 16.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                if (selectedWoman) {
                                    selectedMan = true; selectedWoman = false
                                } else selectedMan = true
                            },
                            shape = RoundedCornerShape(
                                topStart = 9.dp,
                                topEnd = 0.dp,
                                bottomStart = 9.dp,
                                bottomEnd = 0.dp
                            ),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (selectedMan) Accent else SealBrown,
                                contentColor = if (selectedMan) BaseWhite else GrayFaded
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                        ) {
                            Text(
                                stringResource(R.string.sex_man),
                                style = BodySmall
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                if (selectedMan) {
                                    selectedWoman = true; selectedMan = false
                                } else selectedWoman = true
                            },
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 9.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 9.dp
                            ), colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (selectedWoman) Accent else SealBrown,
                                contentColor = if (selectedWoman) BaseWhite else GrayFaded
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                        ) {
                            Text(
                                stringResource(R.string.sex_woman),
                                style = BodySmall
                            )
                        }
                    }
                }
            }

            item() {
                Column() {
                    Spacer(modifier = Modifier.height(28.dp))
                    OutlinedButton(
                        onClick = { onGoToMainClick() },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp),
                        border = if (true)
                            BorderStroke(1.dp, Accent)
                        else BorderStroke(1.dp, GraySilver),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Accent,
                            contentColor = BrightWhite,
                            disabledContainerColor = SealBrown,
                            disabledContentColor = Accent
                        )
                    ) {
                        Text(
                            stringResource(R.string.button_signup),
                            style = Body
                        )
                    }
                    TextButton(
                        onClick = onAccountClick,
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            stringResource(R.string.button_have_account_yet),
                            color = Accent,
                            style = Body
                        )
                    }
                }
            }
        }
    }

}