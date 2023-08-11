package com.stewemetal.takehometemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stewemetal.takehometemplate.design.theme.TakeHomeTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TakeHomeTemplateTheme {
                TakeHomeTemplateApp()
            }
        }
    }
}
