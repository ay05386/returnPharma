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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
                onClick = {  navController.navigate(RETURN_REQUESTS) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log In")
            }
        }
    }
}