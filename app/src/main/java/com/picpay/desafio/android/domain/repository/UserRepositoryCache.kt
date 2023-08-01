package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.data.local.entity.UserEntity

interface UserRepositoryCache {

   suspend fun getAllUser() : List<UserEntity>

   suspend fun upsertAll(user : List<UserEntity>)
}