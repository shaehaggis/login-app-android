package com.example.loginapp.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginapp.model.network.Credentials
import com.example.loginapp.viewmodel.LoginState
import com.example.loginapp.viewmodel.LoginViewModel

@Composable
fun LoginScreenLayout(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
){

    val viewModel: LoginViewModel = viewModel()

    val username = viewModel.username.value
    val password = viewModel.password.value
    val loginState by viewModel.loadingState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            fontSize = 64.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            value = username,
            onValueChange = { viewModel.setUsername(it) },
            placeholder = { Text(text = "Username") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = password,
            onValueChange = { viewModel.setPassword(it) },
            placeholder = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = { viewModel.login(Credentials(username, password)) }) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(60.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val currentLoginState = loginState) {
                is LoginState.Idle -> {}
                is LoginState.Loading -> CircularProgressIndicator()
                is LoginState.Success -> navigateToHome()
                is LoginState.Error -> Text(
                        text = "Error logging in... ${currentLoginState.errorMessage}"
                    )
            }
        }
    }
}