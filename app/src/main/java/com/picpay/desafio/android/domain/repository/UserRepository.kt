package com.picpay.desafio.android.domain.repository

interface UserRepository {

    suspend fun getUser()
}