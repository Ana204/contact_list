package com.contact.list.android.domain.repository

interface SharedPreferencesRepository {

    fun saveTime(timeMilliseconds: Long)

    fun getTime() : Long
}