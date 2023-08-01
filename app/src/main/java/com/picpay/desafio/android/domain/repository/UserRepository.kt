package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUserFromApi() : List<UserEntity>

    suspend fun saveUsersToDataBase() : List<UserEntity>

    fun getAllUser(): List<UserEntity>
}