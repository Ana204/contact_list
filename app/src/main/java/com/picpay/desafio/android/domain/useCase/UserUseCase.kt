package com.picpay.desafio.android.domain.useCase

import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
) {
    suspend fun getUser() : List<User>{
       return repository.getUser()
    }
}