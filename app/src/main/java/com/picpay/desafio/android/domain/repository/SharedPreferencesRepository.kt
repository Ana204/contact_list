package com.picpay.desafio.android.domain.repository

interface SharedPreferencesRepository {

    fun saveTime(timeMilliseconds: Long)

    fun getTime() : Long
}