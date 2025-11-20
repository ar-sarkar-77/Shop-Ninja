package com.anondo.shopninja.views.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anondo.shopninja.core.DataState
import com.anondo.shopninja.data.models.UserLogIn
import com.anondo.shopninja.data.models.UserRegistation
import com.anondo.shopninja.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val userAuth : AuthRepository
): ViewModel() {


    private val logInResponce = MutableLiveData<DataState<UserLogIn>>()

    val logIn_Responce : LiveData<DataState<UserLogIn>> = logInResponce

    fun userLogIn(user : UserLogIn){
        
        logInResponce.postValue(DataState.Loading())


        userAuth.userLogin(user).addOnSuccessListener {
            logInResponce.postValue(DataState.Success(user))
        }.addOnFailureListener {
            logInResponce.postValue(DataState.Error(it.message.toString()))
        }

    }

}