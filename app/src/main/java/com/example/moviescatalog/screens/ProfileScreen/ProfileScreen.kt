package com.example.moviescatalog.screens.ProfileScreen

import android.app.DatePickerDialog
import android.webkit.URLUtil
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviescatalog.R
import com.example.moviescatalog.screens.ProfileScreen.models.ProfileState
import com.example.moviescatalog.screens.ProfileScreen.models.ProfileViewModel
import com.example.moviescatalog.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

@Composable
fun ProfileScreen(
    onReturn: () -> Unit,
    onLogoutClick: () -> Unit,
    profileViewModel: ProfileViewModel
) {

    val profileState by profileViewModel.profileState.observeAsState()
    var refreshCount by remember { mutableStateOf(1) }

    if (profileState is ProfileState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        val login by remember { profileViewModel.loginState }

        val email by remember { profileViewModel.emailState }
        val emailErrorState by rememberSaveable { profileViewModel.emailErrorState }
        var emailErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

        fun validateEmail(email: String) {
            if (email.isBlank()) {
                emailErrorMessage = "Поле должно быть заполнено"
                profileViewModel.emailErrorStateChange(true)
            } else if (!email.matches(Regex("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))\$"))) {
                emailErrorMessage = "Указан некорректный email"
                profileViewModel.emailErrorStateChange(true)
            } else {
                profileViewModel.emailErrorStateChange(false)
            }
        }


        val avatarUrl by remember { profileViewModel.avatarUrlState }
        val avatarUrlErrorState by rememberSaveable { profileViewModel.avatarUrlErrorState }
        var avatarUrlErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

        fun validateAvatarUrl(avatarUrl: String) {
            if(avatarUrl.isNotBlank()) {
                if (!URLUtil.isValidUrl(avatarUrl)) {
                    avatarUrlErrorMessage = "Указана некорректная ссылка"
                    profileViewModel.avatarUrlErrorStateChange(true)
                } else {
                    profileViewModel.avatarUrlErrorStateChange(false)
                }
            } else {
                profileViewModel.avatarUrlErrorStateChange(false)
            }
        }

        val name by remember { profileViewModel.nameState }
        val nameErrorState by rememberSaveable { profileViewModel.nameErrorState }
        var nameErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

        fun validateName(name: String) {
            if (name.isBlank()) {
                nameErrorMessage = "Поле должно быть заполнено"
                profileViewModel.nameErrorStateChange(true)
            } else {
                profileViewModel.nameErrorStateChange(false)
            }
        }

        val birthdate by remember { profileViewModel.birthdateState }
        val birthdateErrorState by rememberSaveable { profileViewModel.birthdateErrorState }
        var birthdateErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }

        fun validateBirthdate(birthdate: LocalDate?) {
            if (birthdate == null) {
                birthdateErrorMessage = "Поле должно быть заполнено"
                profileViewModel.birthdateErrorStateChange(true)
            } else if (birthdate.isAfter(LocalDate.now())) {
                birthdateErrorMessage = "Указана некорректная дата рождения"
                profileViewModel.birthdateErrorStateChange(true)
            } else {
                profileViewModel.birthdateErrorStateChange(false)
            }
        }

        val male by remember { profileViewModel.genderMale }
        val female by remember { profileViewModel.genderFemale }
        val gender by remember { profileViewModel.genderState }
        val genderErrorState by rememberSaveable { profileViewModel.genderErrorState }
        var genderErrorMessage by rememberSaveable { mutableStateOf("Поле должно быть заполнено") }


        validateAvatarUrl(avatarUrl = avatarUrl)


        val correctnessFields by remember { profileViewModel.correctnessFields }

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
                if(avatarUrl.isBlank()) {
                    Image(
                        painter = painterResource(R.drawable.avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(88.dp)
                            .clip(CircleShape)
                    )
                } else {
                    if(!avatarUrlErrorState) {
                        AsyncImage(
                            model = avatarUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(88.dp)
                                .clip(CircleShape)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = login,
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
                            // EMAIL
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(
                                    stringResource(R.string.text_email),
                                    style = Body,
                                    color = GraySilver
                                )
                                Column {
                                    OutlinedTextField(
                                        value = email,
                                        onValueChange = {
                                            profileViewModel.emailChange(it)
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
                                            errorBorderColor = Accent
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Email,
                                            imeAction = ImeAction.Next
                                        ),
                                        shape = RoundedCornerShape(9.dp),
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
                            }
                            // AVATAR URL
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(
                                    stringResource(R.string.text_link),
                                    style = Body,
                                    color = GraySilver
                                )
                                Column {
                                    OutlinedTextField(
                                        value = avatarUrl,
                                        onValueChange = {
                                            profileViewModel.avatarUrlChange(it)
                                            validateAvatarUrl(avatarUrl)
                                        },
                                        trailingIcon = {
                                            if (avatarUrlErrorState)
                                                Icon(Icons.Filled.Error, "error", tint = Accent)
                                        },
                                        isError = avatarUrlErrorState,
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
                                            errorBorderColor = Accent
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Uri,
                                            imeAction = ImeAction.Next
                                        ),
                                        shape = RoundedCornerShape(9.dp),
                                        modifier = Modifier
                                            .fillMaxWidth()

                                    )
                                    if (avatarUrlErrorState) {
                                        Text(
                                            text = avatarUrlErrorMessage,
                                            style = BodySmall,
                                            modifier = Modifier.padding(start = 10.dp)
                                        )
                                    }
                                }
                            }

                            //NAME
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Text(
                                    stringResource(R.string.text_name),
                                    style = Body,
                                    color = GraySilver
                                )
                                Column {

                                    OutlinedTextField(
                                        value = name,
                                        onValueChange = {
                                            profileViewModel.nameChange(it)
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
                                            errorBorderColor = Accent
                                        ),
                                        keyboardOptions = KeyboardOptions(
                                            keyboardType = KeyboardType.Text,
                                            imeAction = ImeAction.Done
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

                            }

                            // BIRTHDATE
                            Text(
                                stringResource(R.string.text_birthdate),
                                style = Body,
                                color = GraySilver
                            )
                            val defaultPickerDate = birthdate ?: LocalDate.now()

                            val mDatePickerDialog = DatePickerDialog(
                                LocalContext.current,
                                { _, year, month, dayOfMonth ->
                                    profileViewModel.birthdateChange(
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
                                        text = if (birthdate == null) stringResource(id = R.string.placeholder_birthdate)
                                        else formatter.format(birthdate),
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
                            Text(
                                stringResource(R.string.text_sex),
                                style = Body,
                                color = GraySilver
                            )
                            Column {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    OutlinedButton(
                                        onClick = {
                                            if (female) {
                                                profileViewModel.genderMaleChanged(true)
                                                profileViewModel.genderFemaleChanged(false)
                                            } else {
                                                profileViewModel.genderMaleChanged(true)
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
                                                profileViewModel.genderFemaleChanged(true)
                                                profileViewModel.genderMaleChanged(false)
                                            } else {
                                                profileViewModel.genderFemaleChanged(true)
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

                    // BUTTONS
                    item() {
                        Column() {
                            Spacer(modifier = Modifier.height(28.dp))
                            OutlinedButton(
                                onClick = profileViewModel::onSaveClick,
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
                                    stringResource(R.string.button_save),
                                    style = Body
                                )
                            }
                            TextButton(
                                onClick = profileViewModel::onLogoutClick,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    stringResource(R.string.button_logout_of_account),
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

    LaunchedEffect(key1 = refreshCount) {
        profileViewModel.loadProfile()
    }

    LaunchedEffect(key1 = profileState, block = {
        when (profileState) {
            is ProfileState.AuthorizationFailed,
            is ProfileState.LogoutSuccessful -> onLogoutClick.invoke()
            is ProfileState.SaveSuccessful -> onReturn.invoke()
            else -> {}
        }
    })

}