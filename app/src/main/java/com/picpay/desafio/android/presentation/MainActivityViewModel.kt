package com.picpay.desafio.android.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.domain.useCase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor (
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _listUser = MutableStateFlow(MainActivityUiState())
    val listUser = _listUser.asStateFlow()

    init {
        listUser()
    }
     private fun listUser() {
        CoroutineScope(Dispatchers.IO).launch {

            val listUserLocal = userUseCase.getUser()

            Log.d("View-Model - ", "USUARIO LOCAL $listUserLocal")

            //val listUser =  userUseCase.saveUsers()
            //Log.i("VIEW-MODEL - ", "LIST USER - $listUser")

            _listUser.update { state -> state.copy(listUser = listUserLocal) }
        }
    }
}