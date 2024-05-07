package com.example.flownavigator.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.example.flownavigator.ui.appcontent.view.AppContent
import com.example.uikit.theme.FlowNavigatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowNavigatorTheme {
                AppContent()
            }
        }
    }
}