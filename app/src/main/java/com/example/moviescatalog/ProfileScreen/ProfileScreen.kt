package com.example.moviescatalog.ProfileScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R

@Composable
fun ProfileScreen() {
    var selectedMan by remember {mutableStateOf(false)}
    var selectedWoman by remember {mutableStateOf(false)}

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(18.dp), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 42.dp, start = 18.dp)) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text("Тест")
        }
        Box(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .padding(top = 145.dp)
                .wrapContentHeight(),
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(22.dp)) {
                item(){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(13.dp), modifier = Modifier
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
                            Text(
                                stringResource(R.string.text_email),
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                singleLine = true,
                                //placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_email)) },
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
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
                            Text(
                                stringResource(R.string.text_link),
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                singleLine = true,
                                //placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_email)) },
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
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
                            Text(
                                stringResource(R.string.text_name),
                            )
                            OutlinedTextField(
                                value = "",
                                onValueChange = { },
                                singleLine = true,
                                //placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_name)) },
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
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(9.dp)) {
                            Text(
                                stringResource(R.string.text_birthdate),
                            )
                            OutlinedTextField(
                                readOnly = true,
                                value = "",
                                onValueChange = {},
                                //placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_birthdate)) },
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
                        }
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
                                Text(stringResource(R.string.sex_man))
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
                                Text(stringResource(R.string.sex_woman))
                            }
                        }
                    }
                }
                item() {
                    Column() {
                        OutlinedButton(
                            onClick = { /*TODO*/ }, modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(1.dp, Color(0xffB7B7B7))
                        ) {
                            Text(
                                stringResource(R.string.button_save),
                                color = Color(0xffEF3A01)
                            )
                        }
                        TextButton(
                            onClick = { /*TODO*/ }, modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                stringResource(R.string.button_log_out_of_account),
                                color = Color(0xffEF3A01)
                            )
                        }
                    }
                }
            }
        }
    }

}