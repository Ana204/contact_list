package com.contact.list.android.domain.repository

import com.contact.list.android.data.local.entity.UserEntity

interface UserRepositoryRemote {

    suspend fun getUserFromApi() : List<UserEntity>

}