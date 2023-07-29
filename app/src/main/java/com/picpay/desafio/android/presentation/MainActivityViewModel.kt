package com.picpay.desafio.android.presentation

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.useCase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor (
    private val userUseCase: UserUseCase
) : ViewModel() {

    suspend fun listUser() : List<User>{
        return userUseCase.getUser()
    }
}