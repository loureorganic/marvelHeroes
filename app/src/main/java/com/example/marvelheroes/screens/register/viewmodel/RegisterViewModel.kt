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
    val booleanCreateUserLiveData: MutableLiveData<Boolean>
}

@HiltViewModel
class RegisterViewModel @Inject constructor(private val service: ServiceRegister) : ViewModelRegister, ViewModel() {

    private val createUserLiveData = MutableLiveData<Boolean>()
    override val booleanCreateUserLiveData: MutableLiveData<Boolean> = createUserLiveData

    override fun createUser(userAccount: UserAccount) {
        viewModelScope.launch {
         val result =  service.createUser(userAccount)
            if (result.data != null) {
                  booleanCreateUserLiveData.postValue(true)
              }
            else {
                booleanCreateUserLiveData.postValue(false)
            }
        }
    }
}