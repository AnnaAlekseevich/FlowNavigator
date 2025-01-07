package com.example.profile.ui.contentExample

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContentExampleViewModel @Inject constructor() : ViewModel() {
    val simpleList = listOf("Item 1", "Item 2", "Item 3")
}