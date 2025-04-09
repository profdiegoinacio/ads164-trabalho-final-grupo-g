package com.example.conectskills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.conectskills.ui.telas.AddServiceScreen
import com.example.conectskills.ui.theme.ConectSkillsTheme
import com.example.conectskills.ui.telas.CadastroScreen
import com.example.conectskills.ui.telas.HomeScreen
import com.example.conectskills.ui.telas.LoginScreen
import com.example.conectskills.ui.telas.MainScreen
import com.example.conectskills.ui.telas.ProfileScreen
import com.example.conectskills.ui.telas.Service
import com.example.conectskills.ui.theme.ConectSkillsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConectSkillsTheme {
                val navController = rememberNavController()

                var services by remember { mutableStateOf(emptyList<Service>()) }

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable("login") {
                        LoginScreen(
                            onLoginSuccess = { navController.navigate("home") },
                            onNavigateToRegister = { navController.navigate("register") }
                        )
                    }

                    composable("register") {
                        CadastroScreen(
                            onRegisterClick = { navController.navigate("home") },
                            onNavigateToLogin = { navController.popBackStack() }
                        )
                    }

                    composable("home") {
                        HomeScreen()
                    }

                    composable("perfil") {
                        ProfileScreen()
                    }

                    composable("main") {
                        MainScreen(
                            onAddServiceClick = { navController.navigate("add-service") },
                            services = services,
                            onServiceAdded = { newService ->
                                services = services + newService
                            },
                        )
                    }
                    composable("add-service") {
                        AddServiceScreen(
                            onBack = { navController.popBackStack() },
                            onSave = { newService ->
                                services = services + newService
                            }
                        )
                    }
                }
            }
        }
    }
}