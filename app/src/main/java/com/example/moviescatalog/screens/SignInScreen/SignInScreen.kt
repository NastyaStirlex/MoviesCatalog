package com.example.moviescatalog.screens.SignInScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.moviescatalog.R
import com.example.moviescatalog.data.Status
import com.example.moviescatalog.data.repository.JwtRepository
import com.example.moviescatalog.screens.SignInScreen.models.SignInViewModel
import com.example.moviescatalog.ui.theme.*
import es.dmoral.toasty.Toasty

@Composable
fun SignInScreen(
    onClickLogin: () -> Unit,
    onRegisterClick: () -> Unit,
    signInViewModel: SignInViewModel,
    context: Context,
    jwtRepository: JwtRepository
) {
    val screenState = signInViewModel.screenState.observeAsState().value

    val login = signInViewModel.loginData.observeAsState().value
    val password = signInViewModel.passwordData.observeAsState().value
    val correctnessFields = signInViewModel.fieldsAreCorrect.observeAsState().value


    if (screenState?.status == Status.LOADING || screenState?.status == Status.ERROR || screenState == null) {
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
                    value = login ?: "",
                    onValueChange = signInViewModel::loginChanged,
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
                    value = password ?: "",
                    textStyle = BodySmall,
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done,
                    ),
                    onValueChange = signInViewModel::passwordChanged,
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
                    onClick = signInViewModel::onClickLogin,
                    enabled = correctnessFields ?: false,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(4.dp),
                    border = if (correctnessFields == true)
                        BorderStroke(1.dp, Accent)
                    else BorderStroke(1.dp, GraySilver),
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
            signInViewModel.screenState.value = null
        }
    } else { // SUCCESS
        jwtRepository.saveToken(context, screenState.data!!.token)
        LaunchedEffect(key1 = screenState.status) {
            if (screenState.status == Status.SUCCESS) {
                onClickLogin.invoke()
            }
        }
    }

}