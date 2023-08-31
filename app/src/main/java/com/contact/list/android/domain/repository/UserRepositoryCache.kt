package com.contact.list.android.domain.repository

import com.contact.list.android.data.local.entity.UserEntity

interface UserRepositoryCache {

   suspend fun getAllUser() : List<UserEntity>

   suspend fun upsertAll(user : List<UserEntity>)
}