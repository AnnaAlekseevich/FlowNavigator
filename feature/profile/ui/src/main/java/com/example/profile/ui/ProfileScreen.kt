package com.example.profile.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.api.ProfileDestination

@Composable
fun ProfileScreen(profileNavController: NavController) {
    Log.d("CheckScreen", "ProfileScreen")
    val userId = "248"
    val route = ProfileDestination.profileSettingsRoute.replace("{${ProfileDestination.idArg}}", userId)
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "ProfileScreen")
            Button(onClick = {
                profileNavController.navigate(route = route)
            }) {
                Text("go to Settings")
            }
        }
    }
}