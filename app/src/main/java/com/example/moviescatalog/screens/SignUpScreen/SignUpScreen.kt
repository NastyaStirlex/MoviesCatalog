package com.example.moviescatalog.screens.SignUpScreen

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.data.Status
import com.example.moviescatalog.data.repository.JwtRepository
import com.example.moviescatalog.screens.SignUpScreen.models.SignUpViewModel
import com.example.moviescatalog.ui.theme.*
import es.dmoral.toasty.Toasty
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.LocalDate


@Composable
fun SignUpScreen(
    onSuccessfullRegisterClick: () -> Unit,
    onAccountClick: () -> Unit,
    signUpViewModel: SignUpViewModel,
    context: Context,
    jwtRepository: JwtRepository
) {
    val login by rememberSaveable { signUpViewModel.loginState }
    val loginErrorState by rememberSaveable { signUpViewModel.loginErrorState }
    var loginErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

    fun validateLogin(login: String) {
        if (login.isBlank()) {
            loginErrorMessage = "Поле должно быть заполнено"
            signUpViewModel.loginErrorStateChange(true)
        } else if (!login.matches(Regex("[a-zA-Z0-9._\\-]+"))) {
            loginErrorMessage = "Указан некорректный логин"
            signUpViewModel.loginErrorStateChange(true)
        } else {
            signUpViewModel.loginErrorStateChange(false)
        }
    }

    val email by remember { signUpViewModel.emailState }
    val emailErrorState by rememberSaveable { signUpViewModel.emailErrorState }
    var emailErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

    fun validateEmail(email: String) {
        if (email.isBlank()) {
            emailErrorMessage = "Поле должно быть заполнено"
            signUpViewModel.emailErrorStateChange(true)
        } else if (!email.matches(Regex("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))\$"))) {
            emailErrorMessage = "Указан некорректный email"
            signUpViewModel.emailErrorStateChange(true)
        } else {
            signUpViewModel.emailErrorStateChange(false)
        }
    }


    val name by remember { signUpViewModel.nameState }
    val nameErrorState by rememberSaveable { signUpViewModel.nameErrorState }
    var nameErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

    fun validateName(name: String) {
        if (name.isBlank()) {
            nameErrorMessage = "Поле должно быть заполнено"
            signUpViewModel.nameErrorStateChange(true)
        } else {
            signUpViewModel.nameErrorStateChange(false)
        }
    }

    val password by remember { signUpViewModel.passwordState }
    val passwordErrorState by rememberSaveable { signUpViewModel.passwordErrorState }
    var passwordErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

    fun validatePassword(password: String) {
        if (password.isBlank()) {
            passwordErrorMessage = "Поле должно быть заполнено"
            signUpViewModel.passwordErrorStateChange(true)
        } else if (password.length < 8) {
            passwordErrorMessage = "Пароль должен быть не менее 8 символов"
            signUpViewModel.passwordErrorStateChange(true)
        } else {
            signUpViewModel.passwordErrorStateChange(false)
        }
    }

    val checkPassword by remember { signUpViewModel.checkPasswordState }
    val checkPasswordErrorState by rememberSaveable { signUpViewModel.checkPasswordErrorState }
    var checkPasswordErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

    fun validateCheckPassword(checkPassword: String) {
        if (checkPassword.isBlank()) {
            checkPasswordErrorMessage = "Поле должно быть заполнено"
            signUpViewModel.checkPasswordErrorStateChange(true)
        } else if (checkPassword != password) {
            checkPasswordErrorMessage = "Пароли не совпадают"
            signUpViewModel.checkPasswordErrorStateChange(true)
        } else {
            signUpViewModel.checkPasswordErrorStateChange(false)
        }
    }

    val birthdate by remember { signUpViewModel.birthdateState }
    val birthdateErrorState by rememberSaveable { signUpViewModel.birthdateErrorState }
    var birthdateErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

    fun validateBirthdate(birthdate: LocalDate?) {
        if (birthdate == null) {
            birthdateErrorMessage = "Поле должно быть заполнено"
            signUpViewModel.birthdateErrorStateChange(true)
        } else if (birthdate.isAfter(LocalDate.now())) {
            birthdateErrorMessage = "Указана некорректная дата рождения"
            signUpViewModel.birthdateErrorStateChange(true)
        } else {
            signUpViewModel.birthdateErrorStateChange(false)
        }
    }

    val male by remember { signUpViewModel.genderMale }
    val female by remember { signUpViewModel.genderFemale }
    val gender by remember { signUpViewModel.genderState }
    val genderErrorState by rememberSaveable { signUpViewModel.genderErrorState }
    var genderErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

    fun validateGender(gender: String) {
        if (gender == "") {
            genderErrorMessage = "Поле должно быть заполнено"
            signUpViewModel.genderErrorStateChange(true)
        } else {
            signUpViewModel.genderErrorStateChange(false)
        }
    }


    val correctnessFields by remember { signUpViewModel.correctnessFields }

    val screenState = signUpViewModel.screenState.observeAsState().value

    if(screenState?.status == Status.LOADING || screenState?.status == Status.ERROR || screenState?.status == null) {
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
                        // LOGIN
                        Column {

                            OutlinedTextField(
                                value = login,
                                onValueChange = {
                                    signUpViewModel.loginChange(it)
                                    validateLogin(login)
                                },
                                trailingIcon = {
                                    if (loginErrorState)
                                        Icon(Icons.Filled.Error, "error", tint = Accent)
                                },
                                isError = loginErrorState,
                                singleLine = true,
                                textStyle = BodySmall,
                                placeholder = {
                                    Text(
                                        stringResource(R.string.text_field_login),
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
                                    errorBorderColor = Accent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            if (loginErrorState) {
                                Text(
                                    text = loginErrorMessage,
                                    style = BodySmall,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                        // EMAIL
                        Column {

                            OutlinedTextField(
                                value = email,
                                onValueChange = {
                                    signUpViewModel.emailChange(it)
                                    validateEmail(email)
                                },
                                trailingIcon = {
                                    if (emailErrorState)
                                        Icon(Icons.Filled.Error, "error", tint = Accent)
                                },
                                isError = emailErrorState,
                                singleLine = true,
                                textStyle = BodySmall,
                                placeholder = {
                                    Text(
                                        stringResource(R.string.text_field_email),
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
                                    errorBorderColor = Accent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Email,
                                    imeAction = ImeAction.Next
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            if (emailErrorState) {
                                Text(
                                    text = emailErrorMessage,
                                    style = BodySmall,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                        // NAME
                        Column {

                            OutlinedTextField(
                                value = name,
                                onValueChange = {
                                    signUpViewModel.nameChange(it)
                                    validateName(name)
                                },
                                trailingIcon = {
                                    if (nameErrorState)
                                        Icon(Icons.Filled.Error, "error", tint = Accent)
                                },
                                isError = nameErrorState,
                                singleLine = true,
                                textStyle = BodySmall,
                                placeholder = {
                                    Text(
                                        stringResource(R.string.text_field_name),
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
                                    errorBorderColor = Accent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            if (nameErrorState) {
                                Text(
                                    text = nameErrorMessage,
                                    style = BodySmall,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                        // PASSWORD
                        Column {

                            OutlinedTextField(
                                value = password,
                                onValueChange = {
                                    signUpViewModel.passwordChange(it)
                                    validatePassword(password)
                                },
                                trailingIcon = {
                                    if (passwordErrorState)
                                        Icon(Icons.Filled.Error, "error", tint = Accent)
                                },
                                isError = passwordErrorState,
                                singleLine = true,
                                textStyle = BodySmall,
                                placeholder = {
                                    Text(
                                        stringResource(R.string.text_field_pass),
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
                                    errorBorderColor = Accent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Next
                                ),
                                visualTransformation = PasswordVisualTransformation(),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            if (passwordErrorState) {
                                Text(
                                    text = passwordErrorMessage,
                                    style = BodySmall,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                        // CHECK PASSWORD
                        Column {

                            OutlinedTextField(
                                value = checkPassword,
                                onValueChange = {
                                    signUpViewModel.checkPasswordChange(it)
                                    validateCheckPassword(checkPassword)
                                },
                                trailingIcon = {
                                    if (checkPasswordErrorState)
                                        Icon(Icons.Filled.Error, "error", tint = Accent)
                                },
                                isError = checkPasswordErrorState,
                                singleLine = true,
                                textStyle = BodySmall,
                                placeholder = {
                                    Text(
                                        stringResource(R.string.text_field_check_pass),
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
                                    errorBorderColor = Accent
                                ),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Done
                                ),
                                visualTransformation = PasswordVisualTransformation(),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            if (checkPasswordErrorState) {
                                Text(
                                    text = checkPasswordErrorMessage,
                                    style = BodySmall,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                        // BIRTHDATE
                        val defaultPickerDate = birthdate ?: LocalDate.now()

                        val mDatePickerDialog = DatePickerDialog(
                            LocalContext.current,
                            { _, year, month, dayOfMonth ->
                                signUpViewModel.birthdateChange(
                                    LocalDate.of(
                                        year,
                                        month + 1,
                                        dayOfMonth
                                    )
                                )
                                validateBirthdate(birthdate)
                            },
                            defaultPickerDate.year,
                            defaultPickerDate.monthValue - 1,
                            defaultPickerDate.dayOfMonth
                        )
                        Column {
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
                                    text = if (birthdate == null) stringResource(id = R.string.text_birthdate)
                                    else birthdate!!.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                                    style = BodySmall,
                                    color = if (birthdate == null) GrayFaded else Accent,
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
                            if (birthdateErrorState) {
                                Text(
                                    text = birthdateErrorMessage,
                                    style = BodySmall,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                        // GENDER
                        Column {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        if (female) {
                                            signUpViewModel.genderMaleChanged(true)
                                            signUpViewModel.genderFemaleChanged(false)
                                            validateGender(gender)
                                        } else {
                                            signUpViewModel.genderMaleChanged(true)
                                            validateGender(gender)
                                        }
                                    },
                                    shape = RoundedCornerShape(
                                        topStart = 8.dp,
                                        topEnd = 0.dp,
                                        bottomStart = 8.dp,
                                        bottomEnd = 0.dp
                                    ),
                                    border = BorderStroke(1.dp, GraySilver),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = if (male) Accent else SealBrown,
                                        contentColor = if (male) BaseWhite else GrayFaded
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
                                        if (male) {
                                            signUpViewModel.genderFemaleChanged(true)
                                            signUpViewModel.genderMaleChanged(false)
                                            validateGender(gender)
                                        } else {
                                            signUpViewModel.genderFemaleChanged(true)
                                            validateGender(gender)
                                        }
                                    },
                                    shape = RoundedCornerShape(
                                        topStart = 0.dp,
                                        topEnd = 8.dp,
                                        bottomStart = 0.dp,
                                        bottomEnd = 8.dp
                                    ),
                                    border = BorderStroke(1.dp, GraySilver),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        containerColor = if (female) Accent else SealBrown,
                                        contentColor = if (female) BaseWhite else GrayFaded
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
                            if (genderErrorState) {
                                Text(
                                    text = genderErrorMessage,
                                    style = BodySmall,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }
                }

                item() {
                    Column() {
                        Spacer(modifier = Modifier.height(28.dp))
                        OutlinedButton(
                            onClick = signUpViewModel::onRegisterClick,
                            enabled = correctnessFields,
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(4.dp),
                            border = if (correctnessFields)
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
        if(screenState?.status == Status.LOADING) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 200.dp)
                )
            }
        } else if(screenState?.status == Status.ERROR ) {
            Toasty.error(LocalContext.current, screenState.error!!, Toast.LENGTH_SHORT, false)
                .show()
            signUpViewModel.screenState.value = null
        }
    } else {
        jwtRepository.saveToken(context, screenState.data!!.token)
        LaunchedEffect(key1 = screenState.status) {
            if (screenState.status == Status.SUCCESS) {
                onSuccessfullRegisterClick.invoke()
            }
        }
    }
}