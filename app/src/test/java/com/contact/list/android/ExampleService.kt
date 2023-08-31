package com.contact.list.android

import com.contact.list.android.data.remote.UserApi
import com.contact.list.android.domain.model.User

class ExampleService(
    private val service: UserApi
) {

    fun example(): List<User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}