package com.example.returnpharma.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(navController: NavController) {
    var ndc by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var manufacturer by remember { mutableStateOf("") }
    var fullQuantity by remember { mutableStateOf("") }
    var partialQuantity by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var lotNumber by remember { mutableStateOf("") }
    var showSuccessMessage by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Item") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = ndc,
                onValueChange = { ndc = it },
                label = { Text("NDC") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = manufacturer,
                onValueChange = { manufacturer = it },
                label = { Text("Manufacturer") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fullQuantity,
                onValueChange = { fullQuantity = it },
                label = { Text("Full Quantity") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = partialQuantity,
                onValueChange = { partialQuantity = it },
                label = { Text("Partial Quantity") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = expirationDate,
                onValueChange = { expirationDate = it },
                label = { Text("Expiration Date (MM/DD/YYYY)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = lotNumber,
                onValueChange = { lotNumber = it },
                label = { Text("Lot Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Here you would typically call a function to add the item
                    // For this example, we'll just show a success message and clear the form
                    showSuccessMessage = true
                    ndc = ""
                    description = ""
                    manufacturer = ""
                    fullQuantity = ""
                    partialQuantity = ""
                    expirationDate = ""
                    lotNumber = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Item")
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("items") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Done - Go to Items")
            }

            if (showSuccessMessage) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Item added successfully!",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}