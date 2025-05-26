package com.bimm.takehomeassignmnent.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bimm.takehomeassignmnent.ui.navigation.MainNavigation

/**
 * Hosts the Compose hierarchy for the app.
 *
 * Applies edge-to-edge rendering and sets the root composable
 * inside a Material3 theme + Scaffold.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

@Composable
private fun App() {
    MaterialTheme {
        Scaffold { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MainNavigation()
            }
        }
    }
}