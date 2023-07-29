package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.UserApi
import com.picpay.desafio.android.domain.repository.UserRepository

class UserRepositoryImpl(
    val userApi: UserApi
) : UserRepository {

    override suspend fun getUser() {
        TODO("Not yet implemented")
    }
}