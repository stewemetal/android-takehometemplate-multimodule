package com.stewemetal.takehometemplate.login.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginState,
    onUserChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Login")
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Image(
                    painter = ColorPainter(MaterialTheme.colorScheme.onBackground),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                )

                TextField(
                    value = state.name,
                    onValueChange = onUserChanged,
                )

                TextField(
                    value = state.password,
                    onValueChange = onPasswordChanged,
                )

                Row(
                    modifier = Modifier
                        .height(40.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AnimatedContent(
                        targetState = state.isLoading,
                        label = "",
                    ) { isLoading ->
                        if (!isLoading) {
                            Button(onClick = onLoginClick) {
                                Text("Login")
                            }
                        } else {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenImplPreview() {
    var state by remember { mutableStateOf(LoginState()) }

    LaunchedEffect(state.isLoading) {
        if (state.isLoading) {
            delay(1000)
            state = state.copy(isLoading = false)
        }
    }

    LoginScreen(
        state = state,
        onUserChanged = { state = state.copy(name = it) },
        onPasswordChanged = { state = state.copy(password = it) },
        onLoginClick = {
            state = state.copy(isLoading = !state.isLoading)
        },
    )
}
