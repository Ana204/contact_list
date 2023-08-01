package com.picpay.desafio.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDataBase : RoomDatabase() {

    abstract val dao: UserDao
}