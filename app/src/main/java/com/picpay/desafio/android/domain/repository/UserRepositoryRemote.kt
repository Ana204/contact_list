package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.local.entity.UserEntity

interface UserRepositoryRemote {

    suspend fun getUserFromApi() : List<UserEntity>

}