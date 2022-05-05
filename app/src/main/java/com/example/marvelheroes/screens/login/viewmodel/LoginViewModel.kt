package com.example.marvelheroes.screens.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.screens.login.model.UserLogin
import com.example.marvelheroes.screens.login.services.LoginServices
import com.example.marvelheroes.screens.login.services.ServicesLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelLogin {
    val booleanLoginUserLiveData: MutableLiveData<Boolean>
    fun loginUser(user: UserLogin)
}

@ViewModelScoped
class LoginViewModel : ViewModel(), ViewModelLogin {

    @Inject
    lateinit var services: ServicesLogin

    private val loginUserLiveData = MutableLiveData<Boolean>()
    override val booleanLoginUserLiveData: MutableLiveData<Boolean> = loginUserLiveData

    override fun loginUser(user: UserLogin) {
        //Call dagger 2
        services = LoginServices()
        viewModelScope.launch {
            val result = services.loginUser(UserLogin("kaiqueguimaraes@gmail.com", "1234567"))
            booleanLoginUserLiveData.postValue(result)
        }
    }
}