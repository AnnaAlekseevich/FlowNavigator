package com.example.home.ui

import androidx.lifecycle.ViewModel
import com.example.home.ui.toolbar.model.UserUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    private val _userModelState = MutableStateFlow(
        UserUiModel(userName = "Fun User", userAvatarUrl = null)
    )
    val userModelState: StateFlow<UserUiModel>
        get() = _userModelState
}