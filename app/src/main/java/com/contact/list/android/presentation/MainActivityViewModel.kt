package com.contact.list.android.presentation

import androidx.lifecycle.ViewModel
import com.contact.list.android.domain.useCase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor (
    private val userUseCase: UserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainActivityUiState())
    val state = _state.asStateFlow()

    init {
        listUser()
    }
     private fun listUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listUsers = userUseCase.getUsers()
                _state.update { stateListUsers -> stateListUsers.copy(listUser = listUsers) }
            }catch (e: Exception){
                _state.update { stateError -> stateError.copy(error = e.message!!) }
            }

        }
    }
}