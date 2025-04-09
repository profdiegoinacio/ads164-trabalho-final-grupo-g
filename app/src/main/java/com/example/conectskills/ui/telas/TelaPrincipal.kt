package com.example.conectskills.ui.telas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(services: List<Service>,
               onAddServiceClick: () -> Unit,
               onServiceAdded: (Service) -> Unit) {
    val services = remember {
        listOf(
            Service("Conserto de Geladeira", "Eletrodomésticos", "Conserto geral e manutenção preventiva", 150.0),
            Service("Manutenção de Ar Condicionado", "Climatização", "Instalação e reparos em geral", 200.0),
            Service("Cuidadora de Crianças", "Cuidados", "Cuidados infantis período integral", 25.0)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ConectSkills") },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddServiceClick) {
                Icon(Icons.Default.Add, "Adicionar serviço")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(services) { service ->
                ServiceItem(service = service)
            }
        }
    }
}

@Composable
fun ServiceItem(service: Service) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {}
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = service.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = service.category,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = service.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Text(
                text = "R$ ${"%.2f".format(service.price)}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

data class Service(
    val title: String,
    val category: String,
    val description: String,
    val price: Double
)