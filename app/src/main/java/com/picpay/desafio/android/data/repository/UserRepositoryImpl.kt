package com.picpay.desafio.android.data.repository

import android.util.Log
import com.picpay.desafio.android.data.remote.UserApi
import com.picpay.desafio.android.domain.entity.User
import com.picpay.desafio.android.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository {

    override suspend fun getUser() : List<User> =
        suspendCoroutine { continuation ->
            userApi.getUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                    if (response.isSuccessful) {
                        val user = response.body()!!

                        continuation.resume(user)
                    } else{
                        val exception = response.errorBody().toString()
                        continuation.resumeWithException(RuntimeException(exception))
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.d("UserRepositoryImpl ", "OnFailure ${t.message}")
                }
            })
        }
    }