package com.example.returnpharma.Screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.returnpharma.viewModel.CreateRequestState
import com.example.returnpharma.viewModel.CreateReturnRequestViewModel
import com.example.returnpharma.viewModel.CreateReturnRequestViewModelFactory
import com.example.returnpharma.model.Pharmacy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReturnRequestScreen(navController: NavController) {
    val viewModel: CreateReturnRequestViewModel = viewModel(
        factory = CreateReturnRequestViewModelFactory()
    )
    val createRequestState by viewModel.createRequestState.collectAsState()
    val pharmaciesState by viewModel.pharmacies.collectAsState()

    var selectedServiceType by remember { mutableStateOf("") }
    var selectedWholesaler by remember { mutableStateOf("") }
    var selectedPharmacy by remember { mutableStateOf<Pharmacy?>(null) }
    var isServiceTypeExpanded by remember { mutableStateOf(false) }
    var isWholesalerExpanded by remember { mutableStateOf(false) }
    var isPharmacyExpanded by remember { mutableStateOf(false) }

    val serviceTypes = listOf("EXPRESS_SERVICE", "FULL_SERVICE")
    val wholesalers = listOf("Wholesaler A", "Wholesaler B", "Wholesaler C") // Replace with actual wholesaler list

    LaunchedEffect(Unit) {
        viewModel.fetchPharmacies()
    }

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
            // Pharmacy Dropdown
            ExposedDropdownMenuBox(
                expanded = isPharmacyExpanded,
                onExpandedChange = { isPharmacyExpanded = it }
            ) {
                TextField(
                    value = selectedPharmacy?.doingBusinessAs ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Pharmacy") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isPharmacyExpanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isPharmacyExpanded,
                    onDismissRequest = { isPharmacyExpanded = false }
                ) {
                    pharmaciesState.forEach { pharmacy ->
                        DropdownMenuItem(
                            text = { pharmacy.doingBusinessAs?.let { Text(it) } },
                            onClick = {
                                selectedPharmacy = pharmacy
                                isPharmacyExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = isServiceTypeExpanded,
                onExpandedChange = { isServiceTypeExpanded = it }
            ) {
                TextField(
                    value = selectedServiceType,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Service Type") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isServiceTypeExpanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isServiceTypeExpanded,
                    onDismissRequest = { isServiceTypeExpanded = false }
                ) {
                    serviceTypes.forEach { serviceType ->
                        DropdownMenuItem(
                            text = { Text(serviceType) },
                            onClick = {
                                selectedServiceType = serviceType
                                isServiceTypeExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            ExposedDropdownMenuBox(
                expanded = isWholesalerExpanded,
                onExpandedChange = { isWholesalerExpanded = it }
            ) {
                TextField(
                    value = selectedWholesaler,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Wholesaler") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isWholesalerExpanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = isWholesalerExpanded,
                    onDismissRequest = { isWholesalerExpanded = false }
                ) {
                    wholesalers.forEach { wholesaler ->
                        DropdownMenuItem(
                            text = { Text(wholesaler) },
                            onClick = {
                                selectedWholesaler = wholesaler
                                isWholesalerExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    selectedPharmacy?.let { pharmacy ->
                        if (pharmacy.pharmacyId != null) {
                            viewModel.createReturnRequest(
                                pharmacy.pharmacyId,
                                selectedServiceType,
                                selectedWholesaler
                            )
                        } else {
                            Log.e("CreateReturnRequestScreen", "Selected pharmacy ID is null")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = selectedPharmacy != null && selectedServiceType.isNotEmpty() && selectedWholesaler.isNotEmpty()
            ) {
                Text("Submit")
            }

            when (val state = createRequestState) {
                is CreateRequestState.Loading -> {
                    CircularProgressIndicator()
                }
                is CreateRequestState.Success -> {
                    LaunchedEffect(state) {
                        navController.navigate("addItem")
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
