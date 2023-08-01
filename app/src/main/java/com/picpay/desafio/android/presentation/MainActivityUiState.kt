package com.picpay.desafio.android.presentation

import com.picpay.desafio.android.data.local.entity.UserEntity

data class MainActivityUiState(
    var listUser: List<UserEntity> = ArrayList()
)
