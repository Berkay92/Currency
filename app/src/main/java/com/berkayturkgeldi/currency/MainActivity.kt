package com.berkayturkgeldi.currency

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.berkayturkgeldi.currency.navigation.SetupMainNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainContent() }
    }

    @Composable
    private fun MainContent() {
        val navController = rememberNavController()
        SetupMainNavigation(navController)
    }
}