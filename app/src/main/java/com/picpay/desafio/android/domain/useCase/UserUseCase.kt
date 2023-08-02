package com.picpay.desafio.android.domain.useCase

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.domain.repository.SharedPreferencesRepository
import com.picpay.desafio.android.domain.repository.UserRepositoryCache
import com.picpay.desafio.android.domain.repository.UserRepositoryRemote
import javax.inject.Inject

class UserUseCase @Inject constructor (
    private val repositoryRemote: UserRepositoryRemote,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    private val repositoryCache: UserRepositoryCache,
) {

    suspend fun getUsers() : List<UserEntity>{
        val listUsersLocal = repositoryCache.getAllUser()
        val currentTime = System.currentTimeMillis()
        val cacheValidUntil = currentTime + 300000

        //se o tempo expirou, buscar os usuarios da API, atualiza o cache eo tempo de cache
        return if (listUsersLocal.isEmpty() || isCacheExpired(currentTime)) {
            val usersFromApi = repositoryRemote.getUserFromApi()
            repositoryCache.upsertAll(usersFromApi)
            sharedPreferencesRepository.saveTime(cacheValidUntil)

            usersFromApi
        } else {
            listUsersLocal //retorna a lista local
        }

    }

    private fun isCacheExpired(currentTime: Long): Boolean {
        val getTime = sharedPreferencesRepository.getTime()
        return getTime < currentTime
    }

}