package com.example.marvelheroes.screens.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.services.ServicesLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelLogin {
    val booleanLoginUserLiveData: MutableLiveData<Boolean>
    fun loginUser(user: UserLogin)
}

@HiltViewModel
class LoginViewModel @Inject constructor(private val services: ServicesLogin): ViewModel(), ViewModelLogin {

    private val loginUserLiveData = MutableLiveData<Boolean>()
    override val booleanLoginUserLiveData: MutableLiveData<Boolean> = loginUserLiveData

    override fun loginUser(user: UserLogin) {
        viewModelScope.launch {
            val result = services.loginUser(UserLogin("kaiqueguimaraes@gmail.com", "1234567"))
            booleanLoginUserLiveData.postValue(result)
        }
    }
}