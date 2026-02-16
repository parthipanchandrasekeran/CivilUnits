package com.civilunits.canada

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.civilunits.canada.navigation.AppNavGraph
import com.civilunits.canada.ui.theme.CivilUnitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CivilUnitsTheme {
                AppNavGraph()
            }
        }
    }
}
