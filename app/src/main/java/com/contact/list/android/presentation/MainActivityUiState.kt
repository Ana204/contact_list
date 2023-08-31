package com.contact.list.android.presentation

import com.contact.list.android.data.local.entity.UserEntity

data class MainActivityUiState(
    var listUser: List<UserEntity> = ArrayList(),
    var error: String = ""
)
