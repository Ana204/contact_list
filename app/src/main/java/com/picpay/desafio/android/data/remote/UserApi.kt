package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.domain.entity.User
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getUsers(): Call<List<User>>
}