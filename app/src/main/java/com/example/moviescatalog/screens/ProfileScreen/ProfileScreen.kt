package com.example.moviescatalog.screens.ProfileScreen

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.moviescatalog.R
import com.example.moviescatalog.data.models.UserState
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun ProfileScreen(navController: NavHostController, onLogoutClick: () -> Unit) {
    var selectedMan by remember {mutableStateOf(false)}
    var selectedWoman by remember {mutableStateOf(false)}
    val mDate = remember { mutableStateOf("") }
    val mTime = remember { mutableStateOf("") }
    var birthDate: String = ""

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
                            val mContext = LocalContext.current
                            val mYear: Int
                            val mMonth: Int
                            val mDay: Int
                            val mHour: Int
                            val mMinute: Int
                            val mSecond: Int

                            val mCalendar = Calendar.getInstance().apply {  }

                            mYear = mCalendar.get(Calendar.YEAR)
                            mMonth = mCalendar.get(Calendar.MONTH)
                            mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
                            mHour = mCalendar.get(Calendar.HOUR_OF_DAY)
                            mMinute = mCalendar.get(Calendar.MINUTE)
                            mSecond = mCalendar.get(Calendar.SECOND)


                            mCalendar.time = Date()

                            val mDatePickerDialog = DatePickerDialog(
                                mContext,
                                { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                                    mDate.value = "$mDayOfMonth.${mMonth + 1}.$mYear"
                                    val localdate = LocalDateTime.of(mYear, mMonth, mDay, mHour, mMinute, mSecond)
                                    val offsetDate = OffsetDateTime.of(localdate, ZoneOffset.UTC)
                                    birthDate = offsetDate.format(DateTimeFormatter.ISO_ZONED_DATE_TIME)
                                    Log.d("stringDATE", birthDate)
                                }, mYear, mMonth, mDay
                            )

                            OutlinedTextField(
                                enabled = false,
                                value = mDate.value ?: "",
                                trailingIcon = {
                                    Icon(
                                        Icons.Default.CalendarMonth,
                                        contentDescription = null,
                                        tint = Color(0xffD1D1D1)
                                    )
                                },
                                onValueChange = { },
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
                                    .clickable(onClick = { mDatePickerDialog.show() })
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
                            onClick = onLogoutClick,
                            modifier = Modifier
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