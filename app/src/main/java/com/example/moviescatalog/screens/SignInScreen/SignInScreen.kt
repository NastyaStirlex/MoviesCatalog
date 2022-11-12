package com.example.moviescatalog.screens.SignInScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviescatalog.R
import com.example.moviescatalog.data.models.FavoritesViewModel
import com.example.moviescatalog.data.models.LoginViewModel
import com.example.moviescatalog.data.models.UserState

@Composable
fun OutlinedTextFieldWithErrorView(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    errorMsg : String = ""
) {
    Column(
        modifier = Modifier
            .padding(
                bottom = if (isError) {
                    0.dp
                } else {
                    10.dp
                }
            )
    ) {
        OutlinedTextField(
            enabled = enabled,
            readOnly = readOnly,
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            singleLine = singleLine,
            textStyle = textStyle,
            label = label,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            interactionSource = interactionSource,
            shape = shape,
            colors = colors
        )
        if (isError){
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun SignInScreen(onclickLogin: (login: String, password: String) -> Unit, onRegisterClick: () -> Unit, loginViewModel: LoginViewModel, loadingProgressBar: Boolean, favoritesViewModel: FavoritesViewModel) {
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
                OutlinedTextFieldWithErrorView(
                    value = login,
                    onValueChange = loginTextUpdate,
                    placeholder = { Text(stringResource(R.string.text_field_login)) },
                    singleLine = true,
                    isError = loginTextError,
                    errorMsg = "Введите корректный логин",
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
                OutlinedTextFieldWithErrorView(
                    value = password,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = passwordTextUpdate,
                    isError = passwordTextError,
                    errorMsg = "Введите корректный пароль",
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
        if (loadingProgressBar) {
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

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
            contentAlignment = Alignment.BottomCenter) {
            Column(modifier = Modifier
            ) {
                OutlinedButton(
                    onClick =  {
                        loginTextError = login.isBlank()//меняется текст ошибок
                        passwordTextError = password.isBlank()
                        if(login.isNotBlank() && password.isNotBlank()) { onclickLogin(login, password)} },
                    modifier = Modifier
                    .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    border = if(true) BorderStroke(1.dp, Color(0xffEF3A01)) else BorderStroke(1.dp, Color(0xffB7B7B7)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xffEF3A01),
                        contentColor = Color(0xffFFFFFF),
                        disabledContainerColor = Color(0xff150D0B),
                        disabledContentColor = Color(0xffEF3A01)
                    )
                ) {
                    Text(stringResource(R.string.out_button_login))
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