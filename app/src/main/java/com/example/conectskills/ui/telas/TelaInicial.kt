package com.example.conectskills.ui.telas

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.Locale

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val items = listOf("main", "profile")

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentDestination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                "main" -> Icon(Icons.Default.Home, contentDescription = null)
                                "profile" -> Icon(Icons.Default.Person, contentDescription = null)
                            }
                        },
                        label = { Text(screen.replaceFirstChar { it.uppercase(Locale.ROOT) }) },
                        selected = currentRoute == screen,
                        onClick = {
                            navController.navigate(screen) {
                                popUpTo(navController.graph.startDestinationId) { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("main") { MainScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}