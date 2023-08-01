package com.picpay.desafio.android.domain.repository

interface SharedPreferencesRepository {

    fun saveTime(timeMilliseconds: String)

    fun getTime() : String?
}