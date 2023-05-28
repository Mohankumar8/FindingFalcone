package com.example.findingfalcon.ui.compose

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.findingfalcon.R
import com.example.findingfalcon.domain.entities.Planet
import com.example.findingfalcon.domain.entities.Vehicle
import com.example.findingfalcon.ui.viewmodel.DestinationViewModel
import com.example.findingfalcon.ui.viewmodel.SharedViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationScreen(
    planets: List<Planet>,
    vehicles: List<Vehicle>,
    onNextButtonClicked: () -> Unit = {},
    viewModel: DestinationViewModel,
    sharedViewModel: SharedViewModel
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedPlanet by rememberSaveable {
        mutableStateOf("")
    }

    var selectedVehicle: Vehicle by remember {
        mutableStateOf(Vehicle(name = "", total_no = 0, max_distance = 0, speed = 1, isSelected = false))
    }

    val loading by viewModel.isLoading.collectAsState()

    /*BackHandler(true) {
        sharedViewModel.updateFindFalconeRequest(
            selectedPlanet,
            selectedVehicle.name,
            selectedVehicle.max_distance/selectedVehicle.speed,
            false
        )
    }*/

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        ExposedDropdownMenuBox(
            modifier = Modifier.padding(bottom = 16.dp),
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = selectedPlanet,
                onValueChange = {},
                label = { Text(stringResource(R.string.select_planet)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                planets.forEach { planet ->
                    if(!sharedViewModel.falconeRequest.value.planet_names.contains(planet.name)) {
                        DropdownMenuItem(
                            onClick = {
                                selectedPlanet = planet.name
                                expanded = false
                            },
                            text = { Text(planet.name) },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }

                }
            }
        }
        vehicles.forEach { item ->
            var count = 0
            var enableRadioBtn = true
            val map = sharedViewModel.falconeRequest.value.vehicle_map
            if(map.contains(item.name)) {
                map[item.name]?.let {
                    if(it == item.total_no) {
                        enableRadioBtn = false
                        count = 0
                    } else {
                        count = it
                    }
                }
            } else {
                enableRadioBtn = true
                count = item.total_no
            }
            Row(
                modifier = Modifier.selectable(
                    selected = selectedVehicle == item,
                    onClick = {
                        selectedVehicle = item
                    },
                    enabled = enableRadioBtn
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedVehicle == item,
                    onClick = {
                        selectedVehicle = item
                    },
                    enabled = enableRadioBtn
                )
                Text(text = item.name + " ($count)")
            }
        }
        // Time taken
        Divider(
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
            thickness = 1.dp
        )
        Text(
            text = "${stringResource(R.string.time_taken)}: ${sharedViewModel.falconeRequest.value.time_taken + (selectedVehicle.max_distance/selectedVehicle.speed)}"
        )
        Button(
            onClick = {
                onNextButtonClicked()
                sharedViewModel.updateFindFalconeRequest(
                    selectedPlanet,
                    selectedVehicle.name,
                    selectedVehicle.max_distance/selectedVehicle.speed,
                    true
                )
            },
            enabled = (selectedPlanet != "" && selectedVehicle.name != ""),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(10)
        ) {
            Text(stringResource(R.string.next))
        }
    }
    AddLoader(loading = loading)
}
