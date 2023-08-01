package com.picpay.desafio.android.domain.useCase

import android.util.Log
import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserUseCase(
    private val repository: UserRepository
) {

    private suspend fun saveApiDataInLocalDatabase() : List<UserEntity> {
        val listUserApi = repository.saveUsersToDataBase()
        Log.d("USER-UseCase", "LISTA DA API $listUserApi")
        return listUserApi
    }

    suspend fun getUserFromLocal() : List<UserEntity>{
        val listUserLocal = repository.getAllUser()
        var listSavedInDatabase: List<UserEntity>


        if (listUserLocal.isEmpty()){
            listSavedInDatabase = saveApiDataInLocalDatabase()

            Log.d("USER-UseCase", "Fazer chamada na api e salvar Lista local $listSavedInDatabase")

        }else{
            listSavedInDatabase = listUserLocal

            Log.i("USER-UseCase", "Lista salva no banco de dados local $listUserLocal")
        }
        return listSavedInDatabase
    }


}