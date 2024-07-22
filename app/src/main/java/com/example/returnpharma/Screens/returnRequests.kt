package com.example.returnpharma.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.returnpharma.Routes
import com.example.returnpharma.Routes.CREATE_RETURN_REQUEST
import com.example.returnpharma.model.ReturnRequest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnRequestsScreen(navController: NavController) {
    val returnRequests = listOf(
        ReturnRequest("001", "2024-07-21", 3, "Pending", "Standard", "Wholesaler A"),
        ReturnRequest("002", "2024-07-20", 2, "Approved", "Express", "Wholesaler B"),
        // Add more sample data as needed
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Return Requests") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(CREATE_RETURN_REQUEST) }
            ) {
                Text("Create Return Request")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyColumn {
                items(returnRequests) { request ->
                    ReturnRequestItem(request, navController)
                    Divider()
                }
            }
        }
    }
}

@Composable
fun ReturnRequestItem(request: ReturnRequest, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("ID: ${request.id}", fontWeight = FontWeight.Bold)
        Text("Created At: ${request.createdAt}")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Number of Items: ${request.numberOfItems}",
                modifier = Modifier.clickable {
                    navController.navigate("items/${request.id}")
                }
            )
            Text("Click to view items", style = MaterialTheme.typography.bodySmall)
        }
        Text("Status: ${request.status}")
        Text("Service Type: ${request.serviceType}")
        Text("Associated Wholesaler: ${request.associatedWholesaler}")
    }
}