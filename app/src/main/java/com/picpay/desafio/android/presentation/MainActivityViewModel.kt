package com.picpay.desafio.android.presentation

import androidx.lifecycle.ViewModel
import com.picpay.desafio.android.domain.useCase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor (
    val userUseCase: UserUseCase
) : ViewModel() {
}