package com.hedimisawi.smartconseiltest.ui.auth_activity.login

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hedimisawi.smartconseiltest.R
import com.hedimisawi.smartconseiltest.ui.auth_activity.AuthUIEvent
import com.hedimisawi.smartconseiltest.ui.auth_activity.Header
import com.hedimisawi.smartconseiltest.ui.auth_activity.navigation.Screens
import com.hedimisawi.smartconseiltest.ui.shared_components.BlueFilledButton
import com.hedimisawi.smartconseiltest.ui.shared_components.BlueOutlinedButton
import com.hedimisawi.smartconseiltest.ui.shared_components.LoadingDialog
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onAuthenticated: () -> Unit,
    navController: NavController,
    snackbarHostState: SnackbarHostState
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
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
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
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(stringResource(R.string.password), fontSize = 16.sp)
                OutlinedTextField(value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    placeholder = { Text(stringResource(R.string.password)) },
                    visualTransformation = if (viewModel.isPasswordVisible) {
                        VisualTransformation.None
                    } else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {

                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )
                if (viewModel.isPasswordTooShort) {
                    Text(
                        stringResource(R.string.password_is_too_short),
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }
                Text(stringResource(R.string.forgot_password),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { }
                        .padding(4.dp))
            }
            BlueFilledButton(
                text = stringResource(R.string.login),
                onClick = { viewModel.loginUser(viewModel.email, viewModel.password) },
                modifier = Modifier.padding(top = 16.dp)
            )

            BlueOutlinedButton(
                text = stringResource(R.string.sign_up),
                onClick = { navController.navigate(Screens.RegisterScreen.route) },
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}