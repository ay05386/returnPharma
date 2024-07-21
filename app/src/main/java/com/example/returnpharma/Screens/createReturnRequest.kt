package com.example.returnpharma.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.returnpharma.model.CreateReturnRequestRequest
import com.example.returnpharma.repository.RxMaxRepository
import com.example.returnpharma.viewModel.CreateRequestState
import com.example.returnpharma.viewModel.CreateReturnRequestViewModel
import com.example.returnpharma.viewModel.CreateReturnRequestViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReturnRequestScreen(navController: NavController) {
    val viewModel: CreateReturnRequestViewModel = viewModel()
    val createRequestState by viewModel.createRequestState.collectAsState()

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
            // ... (rest of your UI code remains the same)

            Button(
                onClick = {
                    // Call the API through ViewModel
                    viewModel.createReturnRequest(
                        "your_pharmacy_id", // Replace with actual pharmacy ID
                        selectedServiceType,
                        selectedWholesaler
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedServiceType.isNotEmpty() && selectedWholesaler.isNotEmpty()
            ) {
                Text("Submit")
            }

            // Handle API response
            when (val state = createRequestState) {
                is CreateRequestState.Loading -> {
                    CircularProgressIndicator()
                }
                is CreateRequestState.Success -> {
                    LaunchedEffect(state) {
                        navController.navigate("addItem/${state.returnRequest.id}")
                    }
                }
                is CreateRequestState.Error -> {
                    Text(state.message, color = MaterialTheme.colorScheme.error)
                }
                else -> {}
            }
        }
    }
}