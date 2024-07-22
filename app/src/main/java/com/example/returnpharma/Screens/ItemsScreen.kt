package com.example.returnpharma.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.returnpharma.model.Item

/*
data class Item(
    val id: String,
    val ndc: String,
    val description: String,
    val manufacturer: String,
    val fullQuantity: Int,
    val partialQuantity: Int,
    val expirationDate: String,
    val lotNumber: String
)
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsScreen(navController: NavController/*, returnRequestId: String*/) {
    var items by remember { mutableStateOf(listOf(
        Item("1", "12345-678-90", "Aspirin 100mg", "Bayer", 100, 0, "12/31/2025", "LOT123"),
        Item("2", "98765-432-10", "Ibuprofen 200mg", "Advil", 50, 10, "06/30/2026", "LOT456")
    )) }

    var showUpdateDialog by remember { mutableStateOf(false) }
    var itemToUpdate by remember { mutableStateOf<Item?>(null) }
    var updatedDescription by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Items for Return Request") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(items) { item ->
                    ItemCard(
                        item = item,
                        onDelete = {
                            items = items.filter { it.id != item.id }
                        },
                        onUpdate = {
                            itemToUpdate = item
                            updatedDescription = item.description
                            showUpdateDialog = true
                        }
                    )
                }
            }
        }
    }

    if (showUpdateDialog) {
        AlertDialog(
            onDismissRequest = { showUpdateDialog = false },
            title = { Text("Update Item Description") },
            text = {
                OutlinedTextField(
                    value = updatedDescription,
                    onValueChange = { updatedDescription = it },
                    label = { Text("New Description") }
                )
            },
            confirmButton = {
                Button(onClick = {
                    itemToUpdate?.let { item ->
                        val updatedItem = item.copy(description = updatedDescription)
                        items = items.map { if (it.id == item.id) updatedItem else it }
                    }
                    showUpdateDialog = false
                }) {
                    Text("Update")
                }
            },
            dismissButton = {
                Button(onClick = { showUpdateDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun ItemCard(item: Item, onDelete: () -> Unit, onUpdate: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("NDC: ${item.ndc}", style = MaterialTheme.typography.bodyMedium)
            Text("Description: ${item.description}")
            Text("Manufacturer: ${item.manufacturer}")
            Text("Full Quantity: ${item.fullQuantity}")
            Text("Partial Quantity: ${item.partialQuantity}")
            Text("Expiration Date: ${item.expirationDate}")
            Text("Lot Number: ${item.lotNumber}")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onUpdate) {
                    Icon(Icons.Default.Edit, contentDescription = "Update")
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}