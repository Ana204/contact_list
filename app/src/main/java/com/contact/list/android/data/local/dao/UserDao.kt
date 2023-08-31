package com.contact.list.android.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.contact.list.android.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Upsert
    suspend fun upsertAll(user : List<UserEntity>)

    @Query("SELECT * FROM user")
    fun getAllUser () : List<UserEntity>

    @Query("DELETE FROM user")
    suspend fun clearAllUser()
}