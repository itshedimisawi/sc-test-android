package com.hedimisawi.smartconseiltest.ui.auth_activity.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hedimisawi.smartconseiltest.R
import com.hedimisawi.smartconseiltest.ui.auth_activity.AuthUIEvent
import com.hedimisawi.smartconseiltest.ui.auth_activity.Header
import com.hedimisawi.smartconseiltest.ui.shared_components.BlueFilledButton
import com.hedimisawi.smartconseiltest.ui.shared_components.LoadingDialog
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel,
    onAuthenticated: () -> Unit,
    snackbarHostState: SnackbarHostState,
    navController: NavController
) {
    val state = viewModel.eventFlow.collectAsState(AuthUIEvent.Empty).value
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(state) {
        when (state) {
            is AuthUIEvent.ShowSnackbar -> {
                scope.launch {
                    snackbarHostState.showSnackbar(message = context.getString(state.message.resId))
                }
            }

            AuthUIEvent.Empty -> {}

            AuthUIEvent.Loading -> {}

            AuthUIEvent.Authenticated -> {
                onAuthenticated()
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        LoadingDialog(state is AuthUIEvent.Loading)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Spacer(modifier = Modifier.padding(top = 8.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(stringResource(R.string.name), fontSize = 16.sp)
                OutlinedTextField(value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    placeholder = { Text(stringResource(R.string.name)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                if (viewModel.isNameEmpty) {
                    Text(
                        stringResource(R.string.name_is_required),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(stringResource(R.string.email), fontSize = 16.sp)
                OutlinedTextField(value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    placeholder = { Text(stringResource(R.string.email)) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )
                if (viewModel.isEmailInvalid) {
                    Text(
                        stringResource(R.string.enter_a_valid_email),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(stringResource(R.string.password), fontSize = 16.sp)
                OutlinedTextField(value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    placeholder = { Text(stringResource(R.string.password)) },
                    visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.isPasswordVisible = !viewModel.isPasswordVisible
                        }) {
                            Icon(
                                painter = if (viewModel.isPasswordVisible) painterResource(id = R.drawable.visibility_on) else painterResource(id = R.drawable.visibility_off),
                                contentDescription = null
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )
                if (viewModel.isPasswordTooShort) {
                    Text(
                        "Password is too short",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(stringResource(R.string.confirm_password), fontSize = 16.sp)
                OutlinedTextField(
                    value = viewModel.confirmPassword,
                    onValueChange = { viewModel.confirmPassword = it },
                    placeholder = { Text(stringResource(R.string.confirm_password)) },
                    visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.isPasswordVisible = !viewModel.isPasswordVisible
                        }) {
                            Icon(
                                painter = if (viewModel.isPasswordVisible) painterResource(id = R.drawable.visibility_on) else painterResource(id = R.drawable.visibility_off) ,
                                contentDescription = null
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )
                if (viewModel.isPasswordNotMatching) {
                    Text(
                        stringResource(R.string.passwords_do_not_match),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            BlueFilledButton(
                text = stringResource(R.string.sign_up),
                onClick = { viewModel.registerUser() },
                modifier = Modifier.padding(top = 16.dp)
            )

            TextButton(onClick = { navController.navigateUp() }) {
                Text(stringResource(R.string.already_have_an_account))
            }
        }
    }
}

