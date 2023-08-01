package com.picpay.desafio.android.domain.useCase

import android.util.Log
import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.domain.repository.SharedPreferencesRepository
import com.picpay.desafio.android.domain.repository.UserRepositoryCache
import com.picpay.desafio.android.domain.repository.UserRepositoryRemote
import java.util.Calendar
import javax.inject.Inject

class UserUseCase @Inject constructor (
    private val repositoryRemote: UserRepositoryRemote,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    private val repositoryCache: UserRepositoryCache,
    private val calendar: Calendar
) {

    suspend fun getUser() : List<UserEntity>{
        val listUserLocal = repositoryCache.getAllUser()
        var listSavedInDatabase: List<UserEntity>

        val currentTime = calendar.timeInMillis

        val cacheValidUntil = currentTime + 300000


        if (listUserLocal.isEmpty()){
            listSavedInDatabase = repositoryRemote.getUserFromApi()
            repositoryCache.upsertAll(listSavedInDatabase)
            sharedPreferencesRepository.saveTime(cacheValidUntil.toString())

        }else{
           val getTime = sharedPreferencesRepository.getTime()?.toLong()!!

            listSavedInDatabase = if(getTime > currentTime){
                listUserLocal
            }else{
                repositoryRemote.getUserFromApi()
            }

            Log.i("", "timeMillis Atual - ${getTime}")

        }
        return listSavedInDatabase
    }


}