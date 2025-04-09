package com.example.conectskills.ui.telas

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.conectskills.data.AuthManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.core.net.toUri

// ProfileScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val authManager = remember { AuthManager(context) }
    val userData = authManager.getUserData()

    var phone by remember { mutableStateOf(userData["phone"] ?: "") }
    var cep by remember { mutableStateOf(userData["cep"] ?: "") }
    var cpf by remember { mutableStateOf(userData["cpf"] ?: "") }
    var habilidades by remember { mutableStateOf(userData["habilidades"] ?: "") }
    var isEditing by remember { mutableStateOf(false) }
    var profileImageUri by remember {
        mutableStateOf(authManager.getProfileImageUri()?.toUri())
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                // Salva a URI como String
                authManager.saveProfileImageUri(it.toString())
                // Atualiza o estado com a nova URI
                profileImageUri = it
            }
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Foto de perfil
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            // Imagem de perfil
            if (profileImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(profileImageUri)
                            .apply {
                                placeholder(android.R.drawable.ic_menu_gallery)
                                error(android.R.drawable.ic_dialog_alert)
                            }
                            .build()
                    ),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Foto padrão",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Botão de edição
            IconButton(
                onClick = { imagePicker.launch("image/*") }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Alterar foto"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Dados do usuário
        Text(
            text = userData["name"] ?: "",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = userData["email"] ?: "",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo para telefone
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Telefone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            enabled = isEditing,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cep,
            onValueChange = { cep = it },
            label = { Text("CEP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            enabled = isEditing,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cpf,
            onValueChange = { cpf = it },
            label = { Text("CPF") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            enabled = isEditing,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = habilidades,
            onValueChange = { habilidades = it },
            label = { Text("Descreva suas habilidades") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            enabled = isEditing,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (isEditing) {
                    authManager.saveUserData(
                        name = userData["name"] ?: "",
                        email = userData["email"] ?: "",
                        password = "", // Manter a senha existente
                        phone = phone,
                        cep = cep,
                        cpf = cpf,
                        habilidades = habilidades,
                        profileImageUri = profileImageUri.toString()
                    )
                }
                isEditing = !isEditing
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isEditing) "Salvar" else "Editar Perfil")
        }
    }
}