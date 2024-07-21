package com.example.returnpharma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.returnpharma.ui.theme.ReturnPharmaTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.returnpharma.Routes.ADD_ITEM
import com.example.returnpharma.Routes.CREATE_RETURN_REQUEST
import com.example.returnpharma.Routes.RETURN_REQUESTS
import com.example.returnpharma.Screens.AddItemScreen
import com.example.returnpharma.Screens.CreateReturnRequestScreen
import com.example.returnpharma.Screens.ReturnRequestsScreen
import com.example.returnpharma.networkModule.SessionManager
import com.example.returnpharma.remote.LoginResponse
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.launch
import androidx.lifecycle.lifecycleScope
import com.example.returnpharma.viewModel.LoginState
import com.example.returnpharma.viewModel.LoginViewModel
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReturnPharmaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "login") {
                        composable("login") { LoginScreen(navController) }
                        composable(RETURN_REQUESTS) { ReturnRequestsScreen(navController)
                        }
                        composable(CREATE_RETURN_REQUEST) { CreateReturnRequestScreen(navController)
                        }
                        composable(ADD_ITEM) { AddItemScreen(navController)
                        }





                    }
                }
            }
        }
    }
}*/
class MainActivity : ComponentActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = LoginViewModel()

        enableEdgeToEdge()
        setContent {
            ReturnPharmaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "login") {
                        composable("login") { LoginScreen(navController) }
                        composable(RETURN_REQUESTS) { ReturnRequestsScreen(navController) }
                        composable(CREATE_RETURN_REQUEST) { CreateReturnRequestScreen(navController) }
                        composable(ADD_ITEM) { AddItemScreen(navController) }
                    }
                }
            }
        }

        // Observe login state changes
        lifecycleScope.launch {
            viewModel.loginState.collect { state ->
                when (state) {
                    is LoginState.Success -> {
                        // Handle successful login if needed
                    }
                    is LoginState.Error -> {
                        // Handle login error if needed
                    }
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel: LoginViewModel = viewModel()
    val loginState by viewModel.loginState.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.login(username, password)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log In")
        }

        when (loginState) {
            is LoginState.Loading -> {
                CircularProgressIndicator()
            }
            is LoginState.Success -> {
                LaunchedEffect(loginState) {
                    val data = (loginState as LoginState.Success).data
                    SessionManager.setToken(data.accessToken)
                    navController.navigate(RETURN_REQUESTS)
                }
            }
            is LoginState.Error -> {
                Text(
                    text = (loginState as LoginState.Error).message,
                    color = androidx.compose.ui.graphics.Color.Red
                )
            }
            else -> {}
        }
    }
}

/*

    @Composable
    fun LoginScreen(navController: NavHostController) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login",
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

/*
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        // In a real app, you would make an API call here
                        // and get the token from the response
                        val dummyResponse = LoginResponse("dummyAccessToken")
                        SessionManager.setToken(dummyResponse.accessToken)
                    }
                    navController.navigate(RETURN_REQUESTS)*/ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log In")
            }
        }
    }
}*/