package com.example.moviescatalog.screens.SignUpScreen

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.screens.SignInScreen.OutlinedTextFieldWithErrorView
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun SignUpScreen(onRegisterClick: (userName: String, name: String, password: String, email: String, birthDate: String, gender: Int) -> Unit, onAccountClick: () -> Unit, registerProgressBar: Boolean) {

    var loginTextError by rememberSaveable { mutableStateOf(false) }
    var login by rememberSaveable { mutableStateOf("") }

    val loginTextUpdate = { data: String ->
        if (data.length <= 100){
            login = data
        }
        if(login.isNotBlank()){
            loginTextError = false
        }
    }

    var emailTextError by rememberSaveable { mutableStateOf(false) }
    var email by rememberSaveable { mutableStateOf("") }

    val emailTextUpdate = { data: String ->
        if (data.length <= 100){
            email = data
        }
        if(email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailTextError = false
        }
    }
    //emailTextError = !(email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())

    var nameTextError by rememberSaveable { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }

    val nameTextUpdate = { data : String ->
        if (data.length <= 100){
            name = data
        }
        if(name.isNotBlank()){
            nameTextError = false
        }
    }


    var passwordTextError by rememberSaveable { mutableStateOf(false) }
    var password by rememberSaveable { mutableStateOf("") }

    val passwordTextUpdate = { data: String ->
        if (data.length <= 100){
            password = data
        }
        if(password.isNotBlank()){
            passwordTextError = false
        }
    }

    var check_passwordTextError by rememberSaveable { mutableStateOf(false) }
    var check_password by rememberSaveable { mutableStateOf("") }

    val check_passwordTextUpdate = { data: String ->
        if (data.length <= 100){
            check_password = data
        }
        if(check_password.isNotBlank()){
            check_passwordTextError = false
        }
    }

    var selectedMan by rememberSaveable {mutableStateOf(false)}
    var selectedWoman by remember {mutableStateOf(false)}
    //val login = rememberSaveable{ mutableStateOf("") }
    //val email = remember{ mutableStateOf("") }
    //val name = remember{ mutableStateOf("") }
    //val password = remember{ mutableStateOf("") }
    //val check_password = remember{ mutableStateOf("") }
    val mDate = remember { mutableStateOf("") }
    val mTime = remember { mutableStateOf("") }
    var birthDate: String = ""
    val fieldsFill = remember { mutableStateOf( login.isNotBlank() && password.isNotBlank()
            && email.isNotBlank() && name.isNotBlank()
            && check_password.isNotBlank() && birthDate.isNotBlank()
            && (selectedMan || selectedWoman)) }
    val passwordsMatch = remember { mutableStateOf(password == check_password) }
    //for request


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
                        OutlinedTextFieldWithErrorView(
                            value = login,
                            onValueChange = loginTextUpdate,
                            singleLine = true,
                            isError = loginTextError,
                            errorMsg = "Введите корректный логин",
                            placeholder = { Text(stringResource(R.string.text_field_login)) },
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

                        OutlinedTextFieldWithErrorView(
                            value = email,
                            onValueChange = emailTextUpdate,
                            singleLine = true,
                            isError = emailTextError,
                            errorMsg = "Введите корректный email",
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
                        OutlinedTextFieldWithErrorView(
                            value = name,
                            onValueChange = nameTextUpdate,
                            singleLine = true,
                            isError = nameTextError,
                            errorMsg = "Введите корректное имя",
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
                        OutlinedTextFieldWithErrorView(
                            value = password,
                            onValueChange = passwordTextUpdate,
                            singleLine = true,
                            isError = passwordTextError,
                            errorMsg = "Введите корректный пароль",
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
                        OutlinedTextFieldWithErrorView(
                            value = check_password,
                            onValueChange = check_passwordTextUpdate,
                            singleLine = true,
                            isError = check_passwordTextError,
                            errorMsg = "Введите корректный пароль",
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
                            placeholder = { Text(stringResource(com.example.moviescatalog.R.string.text_field_birthdate)) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                disabledTextColor = Color(0xffEF3A01),
                                disabledBorderColor = Color(0xffB7B7B7),
                                disabledPlaceholderColor = Color(0xffD1D1D1),
                                focusedBorderColor = Color(0xffEF3A01),
                            ),
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = { mDatePickerDialog.show() })
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
                    if (registerProgressBar) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.85f),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(
                                color = Color.Blue,
                                strokeWidth = 5.dp,
                                modifier = Modifier.size(60.dp)
                            )
                        }
                    }
                }
                item() {
                    Column() {
                        OutlinedButton(
                            onClick = {
                                loginTextError = login.isBlank() //меняется текст ошибок
                                emailTextError = email.isBlank()
                                passwordTextError = password.isBlank()
                                check_passwordTextError = check_password.isBlank()
                                emailTextError = !(email.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                                nameTextError = name.isBlank()
                                if(login.isNotBlank() && password.isNotBlank()
                                    && email.isNotBlank() && name.isNotBlank()
                                    && check_password.isNotBlank() && birthDate.isNotBlank()
                                    && (selectedMan || selectedWoman) && password == check_password)
                                    onRegisterClick(login, name, password,
                                email, birthDate,
                                if(selectedMan) 0 else 1) },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            border = if(true)
                                BorderStroke(1.dp, Color(0xffEF3A01))
                            else BorderStroke(1.dp, Color(0xffB7B7B7)),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color(0xffEF3A01),
                                contentColor = Color(0xffFFFFFF),
                                disabledContainerColor = Color(0xff150D0B),
                                disabledContentColor = Color(0xffEF3A01))
                        ) {
                            Text(
                                stringResource(com.example.moviescatalog.R.string.button_signup),
                                color = Color(0xffFFFFFF)
                            )
                        }
                        TextButton(
                            onClick = onAccountClick, modifier = Modifier
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