package com.contact.list.android.data.remote

import com.contact.list.android.domain.model.User
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    fun getUsers(): Call<List<User>>
}