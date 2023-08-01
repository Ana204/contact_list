package com.picpay.desafio.android.data.repository

import android.util.Log
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.domain.repository.UserRepositoryCache

class UserRepositoryCacheImpl(
    private val userDao: UserDao
) : UserRepositoryCache {

    override suspend fun getAllUser(): List<UserEntity> {
        return userDao.getAllUser()
    }

    override suspend fun upsertAll(user: List<UserEntity>) {
        Log.d("User Repository ", "USER ENTITIES $user")
        userDao.upsertAll(user)
    }

}