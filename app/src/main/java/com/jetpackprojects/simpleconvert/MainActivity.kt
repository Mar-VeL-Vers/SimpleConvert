package com.jetpackprojects.simpleconvert

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jetpackprojects.simpleconvert.ui.theme.SimpleConvertTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleConvertTheme {
                Surface (modifier=Modifier.fillMaxSize()){
                    AppNavPaths()
                }


            }
        }
    }
}

