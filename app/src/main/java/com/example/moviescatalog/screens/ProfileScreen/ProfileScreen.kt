package com.example.moviescatalog.screens.ProfileScreen

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

@Composable
fun ProfileScreen(onLogoutClick: () -> Unit) {
    var selectedMan by remember { mutableStateOf(false) }
    var selectedWoman by remember { mutableStateOf(false) }

    val mDate = remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        // avatar + name
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Login",
                style = H1,
                color = BrightWhite
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // fields
        Box(
            modifier = Modifier
                .wrapContentHeight(),
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(22.dp)) {
                item() {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(13.dp)
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                stringResource(R.string.text_email),
                                style = Body,
                                color = GraySilver
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                singleLine = true,
                                textStyle = BodySmall,
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.placeholder_email),
                                        style = BodySmall,
                                        color = GrayFaded
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
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                stringResource(R.string.text_link),
                                style = Body,
                                color = GraySilver
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                singleLine = true,
                                textStyle = BodySmall,
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.placeholder_link),
                                        style = BodySmall,
                                        color = GrayFaded
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
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Uri,
                                    imeAction = ImeAction.Next
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                stringResource(R.string.text_name),
                                style = Body,
                                color = GraySilver
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                singleLine = true,
                                textStyle = BodySmall,
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.placeholder_name),
                                        style = BodySmall,
                                        color = GrayFaded
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
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Done
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        //birthdate
                        Text(
                            stringResource(R.string.text_birthdate),
                            style = Body,
                            color = GraySilver
                        )
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
                        }

                        // woman / man
                        Text(
                            stringResource(R.string.text_sex),
                            style = Body,
                            color = GraySilver
                        )
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

                // buttons
                item() {
                    Column() {
                        OutlinedButton(
                            onClick = { /*TODO*/ },
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
                                stringResource(R.string.button_save),
                                style = Body
                            )
                        }
                        TextButton(
                            onClick = onLogoutClick,
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                stringResource(R.string.button_log_out_of_account),
                                color = Accent,
                                style = Body
                            )
                        }
                    }
                }
            }
        }
    }
}