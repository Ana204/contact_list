package com.contact.list.android.data.repository

import com.contact.list.android.data.local.entity.UserEntity
import com.contact.list.android.data.remote.UserApi
import com.contact.list.android.domain.model.User
import com.contact.list.android.domain.repository.UserRepositoryRemote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryRemoteImpl(
    private val userApi: UserApi
) : UserRepositoryRemote {

    override suspend fun getUserFromApi() : List<UserEntity> =
        suspendCoroutine { continuation ->
            userApi.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                    if (response.isSuccessful) {
                        val user = response.body()!!

                       val responseUserEntity = user.map { userModel ->
                            UserEntity(
                                id = userModel.id,
                                img = userModel.img,
                                name = userModel.name,
                                username = userModel.username
                            )
                        }
                        continuation.resume(responseUserEntity)
                    } else{
                        val exception = response.errorBody()?.string() ?: "Falha ao buscar contatos"
                        continuation.resumeWithException(RuntimeException(exception))
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
}