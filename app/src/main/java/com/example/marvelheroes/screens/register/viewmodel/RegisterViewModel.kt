package com.example.marvelheroes.screens.register.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelheroes.screens.register.model.UserAccount
import com.example.marvelheroes.screens.register.services.ServiceRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ViewModelRegister {
    fun createUser(userAccount: UserAccount)
    fun validateRegisterData(userAccount: UserAccount): String
    val booleanCreateUserLiveData: MutableLiveData<Boolean>
    val errorBooleanCreateUserLiveData: MutableLiveData<Boolean>
}

@HiltViewModel
class RegisterViewModel @Inject constructor(private val services: ServiceRegister) :
    ViewModelRegister, ViewModel() {

    private val createUserLiveData = MutableLiveData<Boolean>()
    override val booleanCreateUserLiveData: MutableLiveData<Boolean> = createUserLiveData

    private val errorCreateUserLiveData = MutableLiveData<Boolean>()
    override val errorBooleanCreateUserLiveData: MutableLiveData<Boolean> = errorCreateUserLiveData

    override fun createUser(userAccount: UserAccount) {
        viewModelScope.launch {
           try {
               val result = services.createUser(userAccount)
               if (result.data != null) {
                   booleanCreateUserLiveData.postValue(true)
                   errorBooleanCreateUserLiveData.postValue(false)
               } else {
                   booleanCreateUserLiveData.postValue(false)
                   errorBooleanCreateUserLiveData.postValue(false)
               }
           } catch (e: Exception){
               errorBooleanCreateUserLiveData.postValue(true)
           }
        }
    }

    override fun validateRegisterData(userAccount: UserAccount): String {
        return services.validateRegisterData(userAccount)
    }
}