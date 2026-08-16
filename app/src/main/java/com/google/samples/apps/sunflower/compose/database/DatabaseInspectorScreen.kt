/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.compose.database

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.samples.apps.sunflower.data.GardenPlanting
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.viewmodels.DatabaseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseInspectorScreen(
    onBackClick: () -> Unit,
    viewModel: DatabaseViewModel = hiltViewModel()
) {
    val plants by viewModel.plants.collectAsState()
    val gardenPlantings by viewModel.gardenPlantings.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("tuSQL - Inspector de Tablas") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Tabla: plants",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(plants) { plant ->
                PlantRow(plant)
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            }

            item {
                Text(
                    text = "Tabla: garden_plantings",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                )
            }
            items(gardenPlantings) { planting ->
                PlantingRow(planting)
                HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}

@Composable
fun PlantRow(plant: Plant) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "ID: ${plant.plantId}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Nombre: ${plant.name}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Zona: ${plant.growZoneNumber}", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun PlantingRow(planting: GardenPlanting) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "ID Siembra: ${planting.gardenPlantingId}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Plant ID: ${planting.plantId}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Fecha: ${planting.plantDate.time}", style = MaterialTheme.typography.bodySmall)
    }
}
