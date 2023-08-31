package com.contact.list.android.data.repository

import com.contact.list.android.data.local.dao.UserDao
import com.contact.list.android.data.local.entity.UserEntity
import com.contact.list.android.domain.repository.UserRepositoryCache

class UserRepositoryCacheImpl(
    private val userDao: UserDao
) : UserRepositoryCache {

    override suspend fun getAllUser(): List<UserEntity> {
        return userDao.getAllUser()
    }

    override suspend fun upsertAll(user: List<UserEntity>) {
        userDao.upsertAll(user)
    }

}