package com.picpay.desafio.android.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.picpay.desafio.android.domain.repository.SharedPreferencesRepository


class SharedPreferencesRepositoryImpl (
    private val application: Application
) : SharedPreferencesRepository {

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

    override fun saveTime(timeMilliseconds: String) {
      sharedPreferences.edit().putString("timeMilliseconds", timeMilliseconds).apply()
    }

    override fun getTime(): String? {
        return sharedPreferences.getString("timeMilliseconds" , null)
    }
}