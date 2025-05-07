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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hedimisawi.smartconseiltest.ui.auth_activity.AuthUIEvent
import com.hedimisawi.smartconseiltest.ui.auth_activity.Header
import com.hedimisawi.smartconseiltest.ui.shared_components.BlueFilledButton
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
                Text("Name", fontSize = 16.sp)
                OutlinedTextField(value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    placeholder = { Text("Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                if (viewModel.isNameEmpty) {
                    Text(
                        "Name is required",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Email", fontSize = 16.sp)
                OutlinedTextField(value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    placeholder = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )
                if (viewModel.isEmailInvalid) {
                    Text(
                        "Enter a valid email",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Password", fontSize = 16.sp)
                OutlinedTextField(value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    placeholder = { Text("Password") },
                    visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.isPasswordVisible = !viewModel.isPasswordVisible
                        }) {
                            Icon(
                                imageVector = if (viewModel.isPasswordVisible) Icons.Default.Email else Icons.Default.Share,
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
                Text("Confirm Password", fontSize = 16.sp)
                OutlinedTextField(
                    value = viewModel.confirmPassword,
                    onValueChange = { viewModel.confirmPassword = it },
                    placeholder = { Text("Confirm Password") },
                    visualTransformation = if (viewModel.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = {
                            viewModel.isPasswordVisible = !viewModel.isPasswordVisible
                        }) {
                            Icon(
                                imageVector = if (viewModel.isPasswordVisible) Icons.Default.Email else Icons.Default.Email,
                                contentDescription = null
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )
                if (viewModel.isPasswordNotMatching) {
                    Text(
                        "Passwords do not match",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
            }

            BlueFilledButton(
                text = "Sign Up",
                onClick = { viewModel.registerUser() },
                modifier = Modifier.padding(top = 16.dp)
            )

            TextButton(onClick = { navController.navigateUp() }) {
                Text("Already have an account")
            }
        }
    }
}

