package com.example.returnpharma.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReturnRequestScreen(navController: NavController) {
    var selectedServiceType by remember { mutableStateOf("") }
    var selectedWholesaler by remember { mutableStateOf("") }
    var isServiceTypeExpanded by remember { mutableStateOf(false) }
    var isWholesalerExpanded by remember { mutableStateOf(false) }

    val serviceTypes = listOf("EXPRESS_SERVICE", "FULL_SERVICE")
    val wholesalers = listOf("Wholesaler A", "Wholesaler B", "Wholesaler C") // Replace with actual wholesaler list

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Return Request") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text("Service Type", style = MaterialTheme.typography.titleLarge)
            Box(modifier = Modifier.padding(vertical = 8.dp)) {
                OutlinedButton(
                    onClick = { isServiceTypeExpanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(selectedServiceType.ifEmpty { "Select Service Type" })
                }
                DropdownMenu(
                    expanded = isServiceTypeExpanded,
                    onDismissRequest = { isServiceTypeExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    serviceTypes.forEach { serviceType ->
                        DropdownMenuItem(onClick = {
                            selectedServiceType = serviceType
                            isServiceTypeExpanded = false
                        }) {
                            Text(serviceType)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Wholesaler", style = MaterialTheme.typography.titleLarge)
            Box(modifier = Modifier.padding(vertical = 8.dp)) {
                OutlinedButton(
                    onClick = { isWholesalerExpanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(selectedWholesaler.ifEmpty { "Select Wholesaler" })
                }
                DropdownMenu(
                    expanded = isWholesalerExpanded,
                    onDismissRequest = { isWholesalerExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    wholesalers.forEach { wholesaler ->
                        DropdownMenuItem(onClick = {
                            selectedWholesaler = wholesaler
                            isWholesalerExpanded = false
                        }) {
                            Text(wholesaler)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Here you would typically call a function to create the return request
                    // For this example, we'll just navigate to the 'Add Item' screen
                    navController.navigate("addItem")
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedServiceType.isNotEmpty() && selectedWholesaler.isNotEmpty()
            ) {
                Text("Submit")
            }
        }
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {

}
